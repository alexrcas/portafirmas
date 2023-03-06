package net.avantic.story.omitirTarea;


import freemarker.template.Template;
import io.quarkiverse.freemarker.TemplatePath;
import net.avantic.fmw.ModalController;
import net.avantic.fmw.ModalExecutor;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

@Path("/secured")
public class OmitirTareaController implements ModalController<OmitirTareaCommand> {

    @Inject
    ModalExecutor<OmitirTareaCommand> modalExecutor;

    @Inject
    @TemplatePath("omitirTarea.flt")
    Template omitirTarea;

    @Inject
    OmitirTareaFacade facade;

    @GET
    @Path("/omitirTarea")
    @Produces(MediaType.TEXT_HTML)
    @Override
    public String getModal(@Context UriInfo uriInfo) {
        MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
        String idTarea = queryParams.getFirst("idTarea");

        OmitirTareaCommand command = new OmitirTareaCommand();
        command.setIdTarea(Long.valueOf(idTarea));

        return modalExecutor.getModalView(omitirTarea, command);
    }

    @POST
    @Path("/omitirTarea")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Override
    public Response postModal(@BeanParam OmitirTareaCommand command) {
        return modalExecutor.postModalView(command, facade::execute);
    }
}
