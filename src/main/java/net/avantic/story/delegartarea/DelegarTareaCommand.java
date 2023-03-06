package net.avantic.story.delegartarea;

import net.avantic.fmw.command.Command;

import javax.validation.constraints.NotNull;
import javax.ws.rs.FormParam;

public class DelegarTareaCommand extends Command {

    private static final long serialVersionUID = 1L;

    @FormParam("idTarea")
    @NotNull(message = "idTarea es un campo obligatorio")
    private Long idTarea;

    @FormParam("idUsuario")
    @NotNull(message = "Debe seleccionar un usuario")
    private Long idUsuario;

    public Long getIdTarea() {
        return idTarea;
    }

    public void setIdTarea(Long idTarea) {
        this.idTarea = idTarea;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }
}
