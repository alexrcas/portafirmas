package net.avantic.story.showinfocircuito;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import io.quarkiverse.freemarker.TemplatePath;
import net.avantic.model.dto.InformacionCircuitoDto;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

@Path("/portafirmas/secured")
public class ShowInfoCircuitoController {

    @Inject
    @TemplatePath("showInfoCircuito.flt")
    Template showInfoCircuito;

    @Inject
    ShowInfoCircuitoFacade facade;


    @GET
    @Path("/showInfoCircuito/{id}")
    @Produces(MediaType.TEXT_HTML)
    public String descargarDocumento(@PathParam("id") String id) throws TemplateException, IOException {
        StringWriter stringWriter = new StringWriter();

        InformacionCircuitoDto informacionCircuitoDto = facade.findInformacionCircuito(Long.valueOf(id));
        showInfoCircuito.process(Map.of(
                "circuito", informacionCircuitoDto
        ), stringWriter);
        return stringWriter.toString();
    }
}
