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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.StringWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

@Path("/secured")
public class ListarCircuitosRoutingController {

    @Inject
    ListarCircuitosFacade facade;

    @GET
    @Path("/listarCircuitos")
    @Produces(MediaType.TEXT_HTML)
    public Response get() throws TemplateException, IOException, URISyntaxException {
        URI uri = facade.routingRedirect();
        return Response.temporaryRedirect(uri)
                .build();
    }
}