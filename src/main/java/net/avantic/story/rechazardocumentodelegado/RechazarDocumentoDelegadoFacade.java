package net.avantic.story.rechazardocumentodelegado;

import net.avantic.fmw.CommandFacade;
import net.avantic.model.entity.Usuario;

public interface RechazarDocumentoDelegadoFacade extends CommandFacade<RechazarDocumentoDelegadoCommand> {

    Usuario getUsuarioAusente(Long idTarea);
}
