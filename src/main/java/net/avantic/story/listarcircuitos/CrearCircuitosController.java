package net.avantic.story.listarcircuitos;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import io.quarkiverse.freemarker.TemplatePath;
import io.quarkus.security.identity.SecurityIdentity;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.StringWriter;
import java.net.URI;
import java.util.Map;

@Path("/secured")
public class CrearCircuitosController {

    @Inject
    @TemplatePath("sinCircuitos.flt")
    Template sinCircuitos;

    @Inject
    ListarCircuitosFacade facade;

    @Inject
    SecurityIdentity securityIdentity;


    @GET
    @Path("/listarCircuitos/crear")
    @Produces(MediaType.TEXT_HTML)
    public Response get() throws TemplateException, IOException {

        StringWriter stringWriter = new StringWriter();
        sinCircuitos.process(Map.of(
                "active", "CIRCUITOS",
                "username", securityIdentity.getPrincipal().getName()
        ), stringWriter);

        URI uri = facade.crearRoutingRedirect();
        if (!uri.toString().contains("/crear")) {
            return Response.temporaryRedirect(uri)
                    .build();
        }

        return Response.ok(stringWriter.toString())
                .build();
    }


}