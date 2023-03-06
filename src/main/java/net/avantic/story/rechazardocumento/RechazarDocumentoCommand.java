package net.avantic.story.rechazardocumento;

import net.avantic.fmw.command.Command;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.ws.rs.FormParam;

public class RechazarDocumentoCommand extends Command {

    private static final long serialVersionUID = 1L;

    @FormParam("idTarea")
    @NotNull(message = "idTarea es un campo obligatorio")
    private Long idTarea;

    @FormParam("motivo")
    private String motivo;

    public Long getIdTarea() {
        return idTarea;
    }

    public void setIdTarea(Long idTarea) {
        this.idTarea = idTarea;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }
}
