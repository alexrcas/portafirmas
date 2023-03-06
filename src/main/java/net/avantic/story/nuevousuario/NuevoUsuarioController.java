package net.avantic.story.nuevousuario;


import freemarker.template.Template;
import io.quarkiverse.freemarker.TemplatePath;
import net.avantic.fmw.ModalController;
import net.avantic.fmw.ModalExecutor;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

@Path("/portafirmas/secured")
public class NuevoUsuarioController implements ModalController<NuevoUsuarioCommand> {

    @Inject
    ModalExecutor<NuevoUsuarioCommand> modalExecutor;

    @Inject
    @TemplatePath("nuevoUsuario.flt")
    Template eliminarUsuario;

    @Inject
    NuevoUsuarioFacade facade;

    @GET
    @Path("/nuevoUsuario")
    @Produces(MediaType.TEXT_HTML)
    @Override
    public String getModal(@Context UriInfo uriInfo) {
        NuevoUsuarioCommand command = new NuevoUsuarioCommand();
        return modalExecutor.getModalView(eliminarUsuario, command);
    }

    @POST
    @Path("/nuevoUsuario")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Override
    public Response postModal(@BeanParam NuevoUsuarioCommand command) {
        return modalExecutor.postModalView(command, facade::execute);
    }
}
