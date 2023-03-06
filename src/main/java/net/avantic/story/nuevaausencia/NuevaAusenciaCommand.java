package net.avantic.story.nuevaausencia;

import net.avantic.fmw.command.Command;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.ws.rs.FormParam;
import java.util.List;
import java.util.Objects;

public class NuevaAusenciaCommand extends Command {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "idAusente es un campo obligatorio")
    @FormParam("idAusente")
    private Long idAusente;

    @NotNull(message = "idSustituto es un campo obligatorio")
    @FormParam("idSustituto")
    private Long idSustituto;

    public Long getIdAusente() {
        return idAusente;
    }

    public void setIdAusente(Long idAusente) {
        this.idAusente = idAusente;
    }

    public Long getIdSustituto() {
        return idSustituto;
    }

    public void setIdSustituto(Long idSustituto) {
        this.idSustituto = idSustituto;
    }
}
