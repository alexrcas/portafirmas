package net.avantic.story.administrartareas;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import io.quarkiverse.freemarker.TemplatePath;
import io.quarkus.runtime.util.StringUtil;
import io.quarkus.security.identity.SecurityIdentity;
import net.avantic.service.SecurityService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.StringWriter;
import java.net.URI;
import java.util.Map;

@Path("/secured")
public class AdministrarTareasController {

    @Inject
    @TemplatePath("administrarTareas.flt")
    Template administrarTareas;


    @Inject
    @TemplatePath("administrarTareasEmpty.flt")
    Template administrarTareasEmpty;

    @Inject
    AdministrarTareasFacade facade;

    @Inject
    SecurityIdentity securityIdentity;


    @GET
    @Path("/administrarTareas")
    @Produces(MediaType.TEXT_HTML)
    public String get(@QueryParam("idUsuario") String idUsuario) throws TemplateException, IOException {

        StringWriter stringWriter = new StringWriter();

        if (StringUtil.isNullOrEmpty(idUsuario)) {
            administrarTareasEmpty.process(Map.of(
                    "usuarios", facade.listUsuarios(),
                    "username", securityIdentity.getPrincipal().getName(),
                    "active", "ADMINISTRAR"
                    ), stringWriter);

            return stringWriter.toString();
        }


        administrarTareas.process(Map.of(
                "usuarios", facade.listUsuarios(),
                "username", securityIdentity.getPrincipal().getName(),
                "active", "ADMINISTRAR",
                "idUsuario", Long.valueOf(idUsuario),
                "lineasDocumentos", facade.listarTareas(Long.valueOf(idUsuario))
        ), stringWriter);

            return stringWriter.toString();
    }
}
