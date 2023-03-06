package net.avantic.story.nuevocircuito;


import freemarker.template.Template;
import io.quarkiverse.freemarker.TemplatePath;
import net.avantic.fmw.ModalController;
import net.avantic.fmw.ModalExecutor;
import net.avantic.model.dto.UsuarioDto;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;
import java.util.Map;

@Path("/portafirmas/secured")
public class NuevoCircuitoController implements ModalController<NuevoCircuitoCommand> {

    @Inject
    ModalExecutor<NuevoCircuitoCommand> modalExecutor;

    @Inject
    @TemplatePath("nuevoCircuito.flt")
    Template nuevoCircuito;

    @Inject
    NuevoCircuitoFacade facade;

    @GET
    @Path("/nuevoCircuito")
    @Produces(MediaType.TEXT_HTML)
    @Override
    public String getModal(@Context UriInfo uriInfo) {
        NuevoCircuitoCommand command = new NuevoCircuitoCommand();
        List<UsuarioDto> usuarios = facade.listUsuarios();
        return modalExecutor.getModalView(nuevoCircuito, command, Map.of(
                "usuarios", usuarios
        ));
    }

    @POST
    @Path("/nuevoCircuito")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Override
    public Response postModal(@BeanParam NuevoCircuitoCommand command) {
        return modalExecutor.postModalView(command, facade::execute);
    }
}
