package net.avantic.story.listarausencias;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import io.quarkiverse.freemarker.TemplatePath;
import io.quarkus.security.identity.SecurityIdentity;
import net.avantic.model.dto.AusenciaDto;
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
public class ListarAusenciasController {

    @Inject
    @TemplatePath("listarAusencias.flt")
    Template listarAusencias;

    @Inject
    ListarAusenciasFacade facade;

    @Inject
    SecurityIdentity securityIdentity;

    @GET
    @Path("/listarAusencias")
    @Produces(MediaType.TEXT_HTML)
    public String get() throws TemplateException, IOException {
        StringWriter stringWriter = new StringWriter();

        List<AusenciaDto> ausencias = facade.listarAusencias();
        listarAusencias.process(Map.of(
                "ausencias", ausencias,
                "active", "AUSENCIAS",
                "username", securityIdentity.getPrincipal().getName()
        ), stringWriter);
        return stringWriter.toString();
    }
}