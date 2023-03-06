package net.avantic.story.listarausencias;

import net.avantic.model.dto.AusenciaDto;
import net.avantic.model.dto.UsuarioDto;

import java.util.List;

public interface ListarAusenciasFacade {

    List<AusenciaDto> listarAusencias();
}
