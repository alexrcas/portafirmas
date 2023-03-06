package net.avantic.story.validardocumento;


import freemarker.template.Template;
import io.quarkiverse.freemarker.TemplatePath;
import net.avantic.fmw.ModalController;
import net.avantic.fmw.ModalExecutor;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.Map;

@Path("/portafirmas/secured")
public class ValidarDocumentoController implements ModalController<ValidarDocumentoCommand> {

    @Inject
    ModalExecutor<ValidarDocumentoCommand> modalExecutor;

    @Inject
    @TemplatePath("validarDocumento.flt")
    Template firmarDocumento;

    @Inject
    ValidarDocumentoFacade facade;

    @GET
    @Path("/validarDocumento")
    @Produces(MediaType.TEXT_HTML)
    @Override
    public String getModal(@Context UriInfo uriInfo) {
        MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
        String idTarea = queryParams.getFirst("idTarea");

        ValidarDocumentoCommand command = new ValidarDocumentoCommand();
        command.setIdTarea(Long.valueOf(idTarea));

        return modalExecutor.getModalView(firmarDocumento, command, Map.of(
                "contenidoDocumento", facade.getContenidoDocumentoBase64(Long.valueOf(idTarea))
        ));
    }

    @POST
    @Path("/validarDocumento")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Override
    public Response postModal(@BeanParam ValidarDocumentoCommand command) {
        return modalExecutor.postModalView(command, facade::execute);
    }
}
