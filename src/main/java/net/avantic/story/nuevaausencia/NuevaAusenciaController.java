package net.avantic.story.nuevaausencia;


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
public class NuevaAusenciaController implements ModalController<NuevaAusenciaCommand> {

    @Inject
    ModalExecutor<NuevaAusenciaCommand> modalExecutor;

    @Inject
    @TemplatePath("nuevaAusencia.flt")
    Template nuevoCircuito;

    @Inject
    NuevaAusenciaFacade facade;

    @GET
    @Path("/nuevaAusencia")
    @Produces(MediaType.TEXT_HTML)
    @Override
    public String getModal(@Context UriInfo uriInfo) {
        NuevaAusenciaCommand command = new NuevaAusenciaCommand();
        List<UsuarioDto> usuarios = facade.listarUsuariosDisponibles();
        return modalExecutor.getModalView(nuevoCircuito, command, Map.of(
                "usuarios", usuarios
        ));
    }

    @POST
    @Path("/nuevaAusencia")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Override
    public Response postModal(@BeanParam NuevaAusenciaCommand command) {
        return modalExecutor.postModalView(command, facade::execute);
    }
}
