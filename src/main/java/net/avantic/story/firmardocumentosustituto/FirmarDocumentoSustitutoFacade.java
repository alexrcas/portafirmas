package net.avantic.story.firmardocumentosustituto;

import net.avantic.fmw.CommandFacade;
import net.avantic.model.dto.UsuarioDto;

public interface FirmarDocumentoSustitutoFacade extends CommandFacade<FirmarDocumentoSustitutoCommand> {

    UsuarioDto getUsuarioOriginal(Long idTarea);

    String getContenidoDocumentoBase64(Long idTarea);
}
