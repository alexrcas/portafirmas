package net.avantic.story.firmardocumento;


import freemarker.template.Template;
import io.quarkiverse.freemarker.TemplatePath;
import net.avantic.fmw.ModalController;
import net.avantic.fmw.ModalExecutor;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.Map;

@Path("/portafirmas/secured")
public class FirmarDocumentoController implements ModalController<FirmarDocumentoCommand> {

    @Inject
    ModalExecutor<FirmarDocumentoCommand> modalExecutor;

    @Inject
    @TemplatePath("firmarDocumento.flt")
    Template firmarDocumento;

    @Inject
    FirmarDocumentoFacade facade;

    @GET
    @Path("/firmarDocumento")
    @Produces(MediaType.TEXT_HTML)
    @Override
    public String getModal(@Context UriInfo uriInfo) {
        MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
        String idTarea = queryParams.getFirst("idTarea");

        FirmarDocumentoCommand command = new FirmarDocumentoCommand();
        command.setIdTarea(Long.valueOf(idTarea));

        return modalExecutor.getModalView(firmarDocumento, command, Map.of(
                "contenidoDocumento", facade.getContenidoDocumentoBase64(Long.valueOf(idTarea))
        ));
    }

    @POST
    @Path("/firmarDocumento")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Override
    public Response postModal(@BeanParam FirmarDocumentoCommand command) {
        return modalExecutor.postModalView(command, facade::execute);
    }
}
