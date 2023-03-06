package net.avantic.story.listarRechazados;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import io.quarkiverse.freemarker.TemplatePath;
import io.quarkus.security.identity.SecurityIdentity;
import net.avantic.model.dto.LineaDocumentoRechazadoDto;

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
public class ListarRechazadosController {

    @Inject
    @TemplatePath("listarRechazados.flt")
    Template listarPendientes;

    @Inject
    ListarRechazadosFacade facade;

    @Inject
    SecurityIdentity securityIdentity;

    @GET
    @Path("/listarRechazados")
    @Produces(MediaType.TEXT_HTML)
    public String get() throws TemplateException, IOException {
        StringWriter stringWriter = new StringWriter();
        List<LineaDocumentoRechazadoDto> lineasDocumentos = facade.listarDocumentosRechazados();
        listarPendientes.process(Map.of(
                "lineasDocumentos", lineasDocumentos,
                "active", "RECHAZADOS",
                "username", securityIdentity.getPrincipal().getName()
        ), stringWriter);
        return stringWriter.toString();
    }
}