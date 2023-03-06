package net.avantic.story.nuevousuario;

import net.avantic.service.UsuarioService;
import net.avantic.type.DNI;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class NuevoUsuarioFacadeImpl implements NuevoUsuarioFacade {

    @Inject
    UsuarioService usuarioService;

    @Override
    public void execute(NuevoUsuarioCommand command) {
        DNI dni = new DNI(command.getDni());
        String nombre = command.getNombre();
        usuarioService.crearUsuario(dni, nombre);
    }

}
