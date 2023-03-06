package net.avantic.story.nuevocircuito;

import net.avantic.fmw.command.Command;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.ws.rs.FormParam;
import java.util.List;

public class NuevoCircuitoCommand extends Command {

    private static final long serialVersionUID = 1L;

    @NotBlank(message = "Nombre es un campo obligatorio")
    @FormParam("nombre")
    private String nombre;

    @FormParam("idsUsuarios")
    @NotEmpty(message = "Debe seleccionar al menos un usuario")
    private List<Long> idsUsuarios;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Long> getIdsUsuarios() {
        return idsUsuarios;
    }

    public void setIdsUsuarios(List<Long> idsUsuarios) {
        this.idsUsuarios = idsUsuarios;
    }
}
