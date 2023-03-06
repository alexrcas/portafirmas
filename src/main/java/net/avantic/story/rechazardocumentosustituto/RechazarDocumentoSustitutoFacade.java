package net.avantic.story.rechazardocumentosustituto;

import net.avantic.fmw.CommandFacade;
import net.avantic.model.entity.Usuario;

public interface RechazarDocumentoSustitutoFacade extends CommandFacade<RechazarDocumentoSustitutoCommand> {

    Usuario getUsuarioAusente(Long idTarea);
}
