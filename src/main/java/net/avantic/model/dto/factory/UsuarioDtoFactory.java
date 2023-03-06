package net.avantic.model.dto.factory;

import net.avantic.model.dto.UsuarioDto;
import net.avantic.model.entity.Usuario;


public class UsuarioDtoFactory {

    public static UsuarioDto newDto(Usuario usuario) {
        return new UsuarioDto(usuario.getId(), usuario.getDni(), usuario.getNombre());
    }
}
