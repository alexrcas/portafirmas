package net.avantic.story.nuevousuario;

import net.avantic.fmw.command.Command;

import javax.validation.constraints.NotBlank;
import javax.ws.rs.FormParam;

public class NuevoUsuarioCommand extends Command {

    private static final long serialVersionUID = 1L;

    @NotBlank(message = "DNI es un campo obligatorio")
    @FormParam("dni")
    private String dni;

    @NotBlank(message = "Nombre es un campo obligatorio")
    @FormParam("nombre")
    private String nombre;

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
