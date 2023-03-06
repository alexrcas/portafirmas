package net.avantic.story.nuevaausencia;

import net.avantic.fmw.CommandFacade;
import net.avantic.model.dto.UsuarioDto;

import java.util.List;

public interface NuevaAusenciaFacade extends CommandFacade<NuevaAusenciaCommand> {

    List<UsuarioDto> listarUsuariosDisponibles();
}
