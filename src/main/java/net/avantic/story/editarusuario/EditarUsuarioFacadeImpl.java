package net.avantic.story.editarusuario;

import net.avantic.model.entity.Usuario;
import net.avantic.service.UsuarioService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class EditarUsuarioFacadeImpl implements EditarUsuarioFacade {

    @Inject
    UsuarioService usuarioService;

    @Override
    public void execute(EditarUsuarioCommand command) {
        Usuario usuario = usuarioService.findUsuarioById(command.getId()).get();
        usuario.setDni(command.getDni());
        usuario.setNombre(command.getNombre());
        usuarioService.actualizarUsuario(usuario);
    }

    @Override
    public void populateCommand(EditarUsuarioCommand command) {
        Usuario usuario = usuarioService.findUsuarioById(command.getId()).get();
        command.setDni(usuario.getDni());
        command.setNombre(usuario.getNombre());
    }
}
