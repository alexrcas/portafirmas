package net.avantic.story.delegartarea;

import net.avantic.fmw.CommandFacade;
import net.avantic.model.dto.UsuarioDto;

import java.util.List;

public interface DelegarTareaFacade extends CommandFacade<DelegarTareaCommand> {

    List<UsuarioDto> listUsuarios(Long idTarea);
}
