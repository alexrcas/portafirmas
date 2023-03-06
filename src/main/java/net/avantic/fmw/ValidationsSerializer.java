package net.avantic.fmw;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import javax.validation.metadata.PropertyDescriptor;
import java.util.List;
import java.util.Set;


public class ValidationsSerializer {

    public static String serializar(List<Validation> validation) {
        try {
            ObjectMapper mapper = new JsonMapper()
                    .registerModule(new JavaTimeModule())
                    .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            return mapper.writeValueAsString(validation);
        } catch (Throwable exception) {
            throw new RuntimeException(exception);
        }
    }

    public static String serializarRequiredFields(Set<PropertyDescriptor> requiredFields) {
        try {
            ObjectMapper mapper = new JsonMapper()
                    .registerModule(new JavaTimeModule())
                    .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            return mapper.writeValueAsString(requiredFields);
        } catch (Throwable exception) {
            throw new RuntimeException(exception);
        }
    }

}
