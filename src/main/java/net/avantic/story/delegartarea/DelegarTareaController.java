package net.avantic.story.delegartarea;


import freemarker.template.Template;
import io.quarkiverse.freemarker.TemplatePath;
import net.avantic.fmw.ModalController;
import net.avantic.fmw.ModalExecutor;
import net.avantic.model.dto.UsuarioDto;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;
import java.util.Map;

@Path("/secured")
public class DelegarTareaController implements ModalController<DelegarTareaCommand> {

    @Inject
    ModalExecutor<DelegarTareaCommand> modalExecutor;

    @Inject
    @TemplatePath("delegarTarea.flt")
    Template delegarTarea;

    @Inject
    DelegarTareaFacade facade;

    @GET
    @Path("/delegarTarea")
    @Produces(MediaType.TEXT_HTML)
    @Override
    public String getModal(@Context UriInfo uriInfo) {
        MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
        String idTarea = queryParams.getFirst("idTarea");

        DelegarTareaCommand command = new DelegarTareaCommand();
        command.setIdTarea(Long.valueOf(idTarea));
        List<UsuarioDto> usuarios = facade.listUsuarios(Long.valueOf(idTarea));
        command.setIdUsuario(usuarios.get(0).getId());

        return modalExecutor.getModalView(delegarTarea, command, Map.of(
                "usuarios", usuarios)
        );
    }

    @POST
    @Path("/delegarTarea")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Override
    public Response postModal(@BeanParam DelegarTareaCommand command) {
        return modalExecutor.postModalView(command, facade::execute);
    }
}
