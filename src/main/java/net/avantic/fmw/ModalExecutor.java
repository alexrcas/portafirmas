package net.avantic.fmw;

import freemarker.template.Template;
import net.avantic.fmw.command.Command;
import net.avantic.fmw.command.CommandSerializer;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.metadata.BeanDescriptor;
import javax.ws.rs.core.Response;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


@ApplicationScoped
public class ModalExecutor<T extends Command> {

    public String getModalView(Template template, Command command) {

        String serializedCommand = CommandSerializer.serializar(command);
        StringWriter stringWriter = new StringWriter();

        ValidatorFactory validatorFactory = javax.validation.Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        BeanDescriptor beanDescriptor = validator.getConstraintsForClass(command.getClass());

        try {
            template.process(Map.of(
                    "command",
                    serializedCommand,
                    "requiredFields",
                    ValidationsSerializer.serializarRequiredFields(beanDescriptor.getConstrainedProperties())
            ), stringWriter);
            return stringWriter.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String getModalView(Template template, Command command, Map map) {

        String serializedCommand = CommandSerializer.serializar(command);
        StringWriter stringWriter = new StringWriter();

        ValidatorFactory validatorFactory = javax.validation.Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        BeanDescriptor beanDescriptor = validator.getConstraintsForClass(command.getClass());

        Map combinedMap = new HashMap(map);
        combinedMap.put("command", serializedCommand);
        combinedMap.put("requiredFields", ValidationsSerializer.serializarRequiredFields(beanDescriptor.getConstrainedProperties()));

        try {
            template.process(combinedMap, stringWriter);
            return stringWriter.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Transactional
    public Response postModalView(T command, PostModalFunction<T> fn) {

        ValidatorFactory validatorFactory = javax.validation.Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<Command>> violations = validator.validate(command);

        if (violations.isEmpty()) {
            try {
                fn.fn(command);
            } catch (Exception e) {
                return Response.status(500).entity(e.getMessage()).build();
            }
            return Response.ok().build();
        }

        List<Validation> validations = violations.stream()
                .map(violation -> new net.avantic.fmw.Validation(
                        violation.getPropertyPath().toString(), violation.getMessageTemplate()
                ))
                .collect(Collectors.toList());

        return Response.status(400).entity(ValidationsSerializer.serializar(validations)).build();
    }
}
