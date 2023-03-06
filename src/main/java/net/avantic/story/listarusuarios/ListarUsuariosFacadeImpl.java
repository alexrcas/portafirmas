package net.avantic.story.listarusuarios;

import net.avantic.model.dto.UsuarioDto;
import net.avantic.model.dto.factory.UsuarioDtoFactory;
import net.avantic.service.UsuarioService;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class ListarUsuariosFacadeImpl implements ListarUsuariosFacade {

    @Inject
    UsuarioService usuarioService;

    @Override
    @RolesAllowed("ADMIN")
    public List<UsuarioDto> listarUsuarios() {
        return usuarioService.listarUsuarios().stream()
                .map(UsuarioDtoFactory::newDto)
                .collect(Collectors.toList());
    }
}
