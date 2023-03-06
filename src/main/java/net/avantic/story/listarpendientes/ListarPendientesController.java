package net.avantic.story.listarpendientes;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import io.quarkiverse.freemarker.TemplatePath;
import io.quarkus.security.identity.SecurityIdentity;
import net.avantic.model.dto.LineaDocumentoPendienteDto;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.io.StringWriter;
import java.security.Principal;
import java.util.List;
import java.util.Map;

@Path("/secured")
public class ListarPendientesController {

    @Inject
    SecurityIdentity securityIdentity;

    @Inject
    @TemplatePath("listarPendientes.flt")
    Template listarPendientes;

    @Inject
    ListarPendientesFacade facade;

    @GET
    @Path("/listarPendientes")
    @Produces(MediaType.TEXT_HTML)
    public String get() throws TemplateException, IOException {
        StringWriter stringWriter = new StringWriter();
        List<LineaDocumentoPendienteDto> lineasDocumentos = facade.listarDocumentosPendientes();
        listarPendientes.process(Map.of(
                "lineasDocumentos", lineasDocumentos,
                "active", "PENDIENTES",
                "username", securityIdentity.getPrincipal().getName()
        ), stringWriter);
        return stringWriter.toString();
    }
}