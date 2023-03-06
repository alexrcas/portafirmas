package net.avantic.story.login;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import io.quarkiverse.freemarker.TemplatePath;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

@Path("/login")
public class LoginController {

    @Inject
    @TemplatePath("login.flt")
    Template login;

    @GET
    @Path("/")
    @Produces(MediaType.TEXT_HTML)
    public String login() throws TemplateException, IOException {
        StringWriter stringWriter = new StringWriter();
        login.process(Map.of(), stringWriter);
        return stringWriter.toString();
    }
}
