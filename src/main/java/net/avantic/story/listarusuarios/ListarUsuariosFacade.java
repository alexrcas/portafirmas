package net.avantic.story.listarusuarios;

import net.avantic.model.dto.UsuarioDto;

import java.util.List;

public interface ListarUsuariosFacade {

    List<UsuarioDto> listarUsuarios();
}
