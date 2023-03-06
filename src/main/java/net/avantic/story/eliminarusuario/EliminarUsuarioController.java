package net.avantic.story.eliminarusuario;


import freemarker.template.Template;
import io.quarkiverse.freemarker.TemplatePath;
import net.avantic.fmw.ModalController;
import net.avantic.fmw.ModalExecutor;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

@Path("/portafirmas/secured")
public class EliminarUsuarioController implements ModalController<EliminarUsuarioCommand> {

    @Inject
    ModalExecutor<EliminarUsuarioCommand> modalExecutor;

    @Inject
    @TemplatePath("eliminarUsuario.flt")
    Template eliminarUsuario;

    @Inject
    EliminarUsuarioFacade facade;

    @GET
    @Path("/eliminarUsuario")
    @Produces(MediaType.TEXT_HTML)
    @Override
    public String getModal(@Context UriInfo uriInfo) {
        MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
        String id = queryParams.getFirst("id");

        EliminarUsuarioCommand command = new EliminarUsuarioCommand();
        command.setId(Long.valueOf(id));

        return modalExecutor.getModalView(eliminarUsuario, command);
    }

    @POST
    @Path("/eliminarUsuario")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Override
    public Response postModal(@BeanParam EliminarUsuarioCommand command) {
        return modalExecutor.postModalView(command, facade::execute);
    }
}
