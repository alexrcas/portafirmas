package net.avantic.story.firmardocumentosustituto;


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
public class FirmarDocumentoSustitutoController implements ModalController<FirmarDocumentoSustitutoCommand> {

    @Inject
    ModalExecutor<FirmarDocumentoSustitutoCommand> modalExecutor;

    @Inject
    @TemplatePath("firmarDocumentoSustituto.flt")
    Template firmarDocumento;

    @Inject
    FirmarDocumentoSustitutoFacade facade;

    @GET
    @Path("/firmarDocumentoSustituto")
    @Produces(MediaType.TEXT_HTML)
    @Override
    public String getModal(@Context UriInfo uriInfo) {
        MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
        String idTarea = queryParams.getFirst("idTarea");

        FirmarDocumentoSustitutoCommand command = new FirmarDocumentoSustitutoCommand();
        command.setIdTarea(Long.valueOf(idTarea));

        UsuarioDto usuarioAusente = facade.getUsuarioOriginal(Long.valueOf(idTarea));

        return modalExecutor.getModalView(firmarDocumento, command, Map.of(
                "ausente", usuarioAusente,
                "contenidoDocumento", facade.getContenidoDocumentoBase64(Long.valueOf(idTarea))
        ));
    }

    @POST
    @Path("/firmarDocumentoSustituto")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Override
    public Response postModal(@BeanParam FirmarDocumentoSustitutoCommand command) {
        return modalExecutor.postModalView(command, facade::execute);
    }
}
