package net.avantic.story.eliminarusuario;

import net.avantic.fmw.command.Command;

import javax.validation.constraints.NotNull;
import javax.ws.rs.FormParam;

public class EliminarUsuarioCommand extends Command {

    private static final long serialVersionUID = 1L;

    @FormParam("id")
    @NotNull(message = "id es un campo obligatorio")
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
