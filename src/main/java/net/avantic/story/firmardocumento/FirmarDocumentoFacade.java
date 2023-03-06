package net.avantic.story.firmardocumento;

import net.avantic.fmw.CommandFacade;

public interface FirmarDocumentoFacade extends CommandFacade<FirmarDocumentoCommand> {

    String getContenidoDocumentoBase64(Long idTarea);

}
