package net.avantic.story.rechazardocumento;


import freemarker.template.Template;
import io.quarkiverse.freemarker.TemplatePath;
import net.avantic.fmw.ModalController;
import net.avantic.fmw.ModalExecutor;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

@Path("/portafirmas/secured")
public class RechazarDocumentoController implements ModalController<RechazarDocumentoCommand> {

    @Inject
    ModalExecutor<RechazarDocumentoCommand> modalExecutor;

    @Inject
    @TemplatePath("rechazarDocumento.flt")
    Template rechazarDocumento;

    @Inject
    RechazarDocumentoFacade facade;

    @GET
    @Path("/rechazarDocumento")
    @Produces(MediaType.TEXT_HTML)
    @Override
    public String getModal(@Context UriInfo uriInfo) {
        MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
        String idTarea = queryParams.getFirst("idTarea");

        RechazarDocumentoCommand command = new RechazarDocumentoCommand();
        command.setIdTarea(Long.valueOf(idTarea));

        return modalExecutor.getModalView(rechazarDocumento, command);
    }

    @POST
    @Path("/rechazarDocumento")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Override
    public Response postModal(@BeanParam RechazarDocumentoCommand command) {
        return modalExecutor.postModalView(command, facade::execute);
    }
}
