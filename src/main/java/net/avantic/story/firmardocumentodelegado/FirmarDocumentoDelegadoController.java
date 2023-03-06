package net.avantic.story.firmardocumentodelegado;


import freemarker.template.Template;
import io.quarkiverse.freemarker.TemplatePath;
import net.avantic.fmw.ModalController;
import net.avantic.fmw.ModalExecutor;
import net.avantic.model.dto.UsuarioDto;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.Map;

@Path("/portafirmas/secured")
public class FirmarDocumentoDelegadoController implements ModalController<FirmarDocumentoDelegadoCommand> {

    @Inject
    ModalExecutor<FirmarDocumentoDelegadoCommand> modalExecutor;

    @Inject
    @TemplatePath("firmarDocumentoDelegado.flt")
    Template firmarDocumento;

    @Inject
    FirmarDocumentoDelegadoFacade facade;

    @GET
    @Path("/firmarDocumentoDelegado")
    @Produces(MediaType.TEXT_HTML)
    @Override
    public String getModal(@Context UriInfo uriInfo) {
        MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
        String idTarea = queryParams.getFirst("idTarea");

        FirmarDocumentoDelegadoCommand command = new FirmarDocumentoDelegadoCommand();
        command.setIdTarea(Long.valueOf(idTarea));

        UsuarioDto usuarioAusente = facade.getUsuarioOriginal(Long.valueOf(idTarea));

        return modalExecutor.getModalView(firmarDocumento, command, Map.of(
                "ausente", usuarioAusente,
                "contenidoDocumento", facade.getContenidoDocumentoBase64(Long.valueOf(idTarea))
        ));
    }

    @POST
    @Path("/firmarDocumentoDelegado")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Override
    public Response postModal(@BeanParam FirmarDocumentoDelegadoCommand command) {
        return modalExecutor.postModalView(command, facade::execute);
    }
}
