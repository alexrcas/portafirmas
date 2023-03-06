package net.avantic.story.firmardocumento;

import net.avantic.fmw.command.Command;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.ws.rs.FormParam;

public class FirmarDocumentoCommand extends Command {

    private static final long serialVersionUID = 1L;

    @FormParam("idTarea")
    @NotNull(message = "idTarea es un campo obligatorio")
    private Long idTarea;

    @FormParam("firma")
    @NotBlank
    private String firma;

    public Long getIdTarea() {
        return idTarea;
    }

    public void setIdTarea(Long idTarea) {
        this.idTarea = idTarea;
    }

    public String getFirma() {
        return firma;
    }

    public void setFirma(String firma) {
        this.firma = firma;
    }
}
