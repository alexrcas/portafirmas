package net.avantic.story.rechazardocumentosustituto;


import freemarker.template.Template;
import io.quarkiverse.freemarker.TemplatePath;
import net.avantic.fmw.ModalController;
import net.avantic.fmw.ModalExecutor;
import net.avantic.model.entity.Usuario;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.Map;

@Path("/portafirmas/secured")
public class RechazarDocumentoSustitutoController implements ModalController<RechazarDocumentoSustitutoCommand> {

    @Inject
    ModalExecutor<RechazarDocumentoSustitutoCommand> modalExecutor;

    @Inject
    @TemplatePath("rechazarDocumentoSustituto.flt")
    Template rechazarDocumento;

    @Inject
    RechazarDocumentoSustitutoFacade facade;

    @GET
    @Path("/rechazarDocumentoSustituto")
    @Produces(MediaType.TEXT_HTML)
    @Override
    public String getModal(@Context UriInfo uriInfo) {
        MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
        String idTarea = queryParams.getFirst("idTarea");

        Usuario ausente = facade.getUsuarioAusente(Long.valueOf(idTarea));

        RechazarDocumentoSustitutoCommand command = new RechazarDocumentoSustitutoCommand();
        command.setIdTarea(Long.valueOf(idTarea));

        return modalExecutor.getModalView(rechazarDocumento, command, Map.of(
                "ausente", ausente
        ));
    }

    @POST
    @Path("/rechazarDocumentoSustituto")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Override
    public Response postModal(@BeanParam RechazarDocumentoSustitutoCommand command) {
        return modalExecutor.postModalView(command, facade::execute);
    }
}
