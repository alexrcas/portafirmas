package net.avantic.story.editarusuario;

import net.avantic.fmw.command.Command;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.ws.rs.FormParam;

public class EditarUsuarioCommand extends Command {

    private static final long serialVersionUID = 1L;

    @FormParam("id")
    @NotNull(message = "id es un campo obligatorio")
    private Long id;

    @NotBlank(message = "DNI es un campo obligatorio")
    @FormParam("dni")
    private String dni;

    @NotBlank(message = "Nombre es un campo obligatorio")
    @FormParam("nombre")
    private String nombre;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
