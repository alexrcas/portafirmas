package net.avantic.story.validardocumento;

import net.avantic.fmw.CommandFacade;

public interface ValidarDocumentoFacade extends CommandFacade<ValidarDocumentoCommand> {

    String getContenidoDocumentoBase64(Long idTarea);

}
