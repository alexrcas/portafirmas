package net.avantic.story.listarusuarios;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import io.quarkiverse.freemarker.TemplatePath;
import io.quarkus.security.identity.SecurityIdentity;
import net.avantic.model.dto.UsuarioDto;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.io.StringWriter;
import java.util.List;
import java.util.Map;

@Path("/secured")
public class ListarUsuariosController {

    @Inject
    @TemplatePath("listarUsuarios.flt")
    Template listarUsuarios;

    @Inject
    ListarUsuariosFacade facade;

    @Inject
    SecurityIdentity securityIdentity;

    @GET
    @Path("/listarUsuarios")
    @Produces(MediaType.TEXT_HTML)
    public String get() throws TemplateException, IOException {
        StringWriter stringWriter = new StringWriter();
        List<UsuarioDto> usuarios = facade.listarUsuarios();
        listarUsuarios.process(Map.of(
                "usuarios", usuarios,
                "active", "USUARIOS",
                "username", securityIdentity.getPrincipal().getName()
        ), stringWriter);
        return stringWriter.toString();
    }
}