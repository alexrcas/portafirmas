package net.avantic.story.descargardocumento;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import io.quarkiverse.freemarker.TemplatePath;
import net.avantic.service.RepositorioService;
import net.avantic.type.UuidDocumento;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.Map;
import java.util.UUID;

@Path("/portafirmas/secured")
public class DescargarDocumentoController {

    @Inject
    @TemplatePath("descargarDocumento.flt")
    Template descargarDocumento;

    @Inject
    DescargarDocumentoFacade facade;


    @GET
    @Path("/descargarDocumento/{uuid}")
    @Produces(MediaType.TEXT_HTML)
    public String descargarDocumento(@PathParam("uuid") String uuid) throws TemplateException, IOException {
        StringWriter stringWriter = new StringWriter();
        descargarDocumento.process(Map.of(
                "uuid", uuid
        ), stringWriter);
        return stringWriter.toString();
    }

    @Path("/descargarContenido/{uuid}")
    @GET
    @Produces("application/pdf")
    public InputStream descargarContenidoDocumento(@PathParam("uuid") String uuid) {
        return facade.descargarContenidoDocumento(uuid);
    }
}
