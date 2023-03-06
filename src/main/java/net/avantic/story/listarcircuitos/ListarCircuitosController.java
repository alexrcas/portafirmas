package net.avantic.story.listarcircuitos;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import io.quarkiverse.freemarker.TemplatePath;
import io.quarkus.security.identity.SecurityIdentity;
import net.avantic.model.dto.CircuitoDto;
import net.avantic.model.dto.UsuarioDto;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.*;
import java.io.IOException;
import java.io.StringWriter;
import java.util.List;
import java.util.Map;

@Path("/secured")
public class ListarCircuitosController {

    @Inject
    @TemplatePath("listarCircuitos.flt")
    Template listarUsuarios;

    @Inject
    ListarCircuitosFacade facade;

    @Inject
    SecurityIdentity securityIdentity;

    @GET
    @Path("/listarCircuitos/{id}")
    @Produces(MediaType.TEXT_HTML)
    public String get(@PathParam("id") String id) throws TemplateException, IOException {
        StringWriter stringWriter = new StringWriter();
        List<UsuarioDto> usuarios = facade.listarUsuariosByCircuito(Long.valueOf(id));
        List<CircuitoDto> circuitos = facade.listarCircuitos();
        listarUsuarios.process(Map.of(
                "circuitos", circuitos,
                "usuarios", usuarios,
                "active", "CIRCUITOS",
                "idCircuito", Long.valueOf(id),
                "username", securityIdentity.getPrincipal().getName()
        ), stringWriter);
        return stringWriter.toString();
    }



}