package net.avantic.story.eliminarusuario;

import net.avantic.service.UsuarioService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class EliminarUsuarioFacadeImpl implements EliminarUsuarioFacade {

    @Inject
    UsuarioService usuarioService;

    @Override
    public void execute(EliminarUsuarioCommand command) {
        usuarioService.eliminarUsuario(command.getId());
    }

}
