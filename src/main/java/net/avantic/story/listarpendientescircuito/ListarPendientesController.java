package net.avantic.story.listarpendientescircuito;

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
import java.util.List;
import java.util.Map;

@Path("/secured")
public class ListarPendientesController {

    @Inject
    SecurityIdentity securityIdentity;

    @Inject
    @TemplatePath("listarPendientesCircuito.flt")
    Template listarPendientesCircuito;

    @Inject
    ListarPendientesCircuitoFacade facade;

    @GET
    @Path("/listarPendientesCircuito")
    @Produces(MediaType.TEXT_HTML)
    public String get() throws TemplateException, IOException {
        StringWriter stringWriter = new StringWriter();
        List<LineaDocumentoPendienteDto> lineasDocumentos = facade.listarDocumentosPendientesCircuito();
        listarPendientesCircuito.process(Map.of(
                "lineasDocumentos", lineasDocumentos,
                "active", "PENDIENTESCIRCUITO",
                "username", securityIdentity.getPrincipal().getName()
        ), stringWriter);
        return stringWriter.toString();
    }
}