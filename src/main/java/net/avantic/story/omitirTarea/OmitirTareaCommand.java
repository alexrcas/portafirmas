package net.avantic.story.omitirTarea;

import net.avantic.fmw.command.Command;

import javax.validation.constraints.NotNull;
import javax.ws.rs.FormParam;

public class OmitirTareaCommand extends Command {

    private static final long serialVersionUID = 1L;

    @FormParam("idTarea")
    @NotNull(message = "idTarea es un campo obligatorio")
    private Long idTarea;

    public Long getIdTarea() {
        return idTarea;
    }

    public void setIdTarea(Long idTarea) {
        this.idTarea = idTarea;
    }

}
