package net.avantic.story.firmardocumentodelegado;

import net.avantic.fmw.CommandFacade;
import net.avantic.model.dto.UsuarioDto;

public interface FirmarDocumentoDelegadoFacade extends CommandFacade<FirmarDocumentoDelegadoCommand> {

    UsuarioDto getUsuarioOriginal(Long idTarea);

    String getContenidoDocumentoBase64(Long idTarea);
}
