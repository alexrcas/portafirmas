package net.avantic.story.rechazardocumentodelegado;


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
public class RechazarDocumentoDelegadoController implements ModalController<RechazarDocumentoDelegadoCommand> {

    @Inject
    ModalExecutor<RechazarDocumentoDelegadoCommand> modalExecutor;

    @Inject
    @TemplatePath("rechazarDocumentoDelegado.flt")
    Template rechazarDocumento;

    @Inject
    RechazarDocumentoDelegadoFacade facade;

    @GET
    @Path("/rechazarDocumentoDelegado")
    @Produces(MediaType.TEXT_HTML)
    @Override
    public String getModal(@Context UriInfo uriInfo) {
        MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
        String idTarea = queryParams.getFirst("idTarea");

        Usuario ausente = facade.getUsuarioAusente(Long.valueOf(idTarea));

        RechazarDocumentoDelegadoCommand command = new RechazarDocumentoDelegadoCommand();
        command.setIdTarea(Long.valueOf(idTarea));

        return modalExecutor.getModalView(rechazarDocumento, command, Map.of(
                "ausente", ausente
        ));
    }

    @POST
    @Path("/rechazarDocumentoDelegado")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Override
    public Response postModal(@BeanParam RechazarDocumentoDelegadoCommand command) {
        return modalExecutor.postModalView(command, facade::execute);
    }
}
