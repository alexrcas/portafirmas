package net.avantic.story.administrartareas;

import net.avantic.model.dto.UsuarioDto;
import net.avantic.model.dto.LineaAdministrarDocumentoDto;

import java.util.List;

public interface AdministrarTareasFacade {

    List<UsuarioDto> listUsuarios();

    List<LineaAdministrarDocumentoDto> listarTareas(Long idUsuario);
}
