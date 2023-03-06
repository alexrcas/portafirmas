package net.avantic.story.listarfirmados;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import io.quarkiverse.freemarker.TemplatePath;
import io.quarkus.security.identity.SecurityIdentity;
import net.avantic.model.dto.LineaDocumentoFirmadoDto;
import net.avantic.service.SecurityService;
import org.apache.sshd.common.util.security.SecurityUtils;

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
public class ListarFirmadosController {

    @Inject
    @TemplatePath("listarFirmados.flt")
    Template listarFirmados;

    @Inject
    ListarFirmadosFacade facade;

    @Inject
    SecurityIdentity securityIdentity;

    @GET
    @Path("/listarFirmados")
    @Produces(MediaType.TEXT_HTML)
    public String get() throws TemplateException, IOException {

        StringWriter stringWriter = new StringWriter();
        List<LineaDocumentoFirmadoDto> lineasDocumentos = facade.listarDocumentosFirmados();
        listarFirmados.process(Map.of(
                "lineasDocumentos", lineasDocumentos,
                "active", "FIRMADOS",
                "username", securityIdentity.getPrincipal().getName()
        ), stringWriter);
        return stringWriter.toString();
    }
}