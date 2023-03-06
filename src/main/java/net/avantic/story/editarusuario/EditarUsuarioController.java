package net.avantic.story.editarusuario;


import freemarker.template.Template;
import io.quarkiverse.freemarker.TemplatePath;
import net.avantic.fmw.ModalController;
import net.avantic.fmw.ModalExecutor;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

@Path("/portafirmas/secured")
public class EditarUsuarioController implements ModalController<EditarUsuarioCommand> {

    @Inject
    ModalExecutor<EditarUsuarioCommand> modalExecutor;

    @Inject
    @TemplatePath("editarUsuario.flt")
    Template editarUsuario;

    @Inject
    EditarUsuarioFacade facade;

    @GET
    @Path("/editarUsuario")
    @Produces(MediaType.TEXT_HTML)
    @Override
    public String getModal(@Context UriInfo uriInfo) {
        MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
        String id = queryParams.getFirst("id");
        EditarUsuarioCommand command = new EditarUsuarioCommand();
        command.setId(Long.valueOf(id));
        facade.populateCommand(command);
        return modalExecutor.getModalView(editarUsuario, command);
    }

    @POST
    @Path("/editarUsuario")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Override
    public Response postModal(@BeanParam EditarUsuarioCommand command) {
        return modalExecutor.postModalView(command, facade::execute);
    }
}
