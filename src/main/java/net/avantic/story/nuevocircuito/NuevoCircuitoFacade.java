package net.avantic.story.nuevocircuito;

import net.avantic.fmw.CommandFacade;
import net.avantic.model.dto.UsuarioDto;

import java.util.List;

public interface NuevoCircuitoFacade extends CommandFacade<NuevoCircuitoCommand> {

    List<UsuarioDto> listUsuarios();
}
