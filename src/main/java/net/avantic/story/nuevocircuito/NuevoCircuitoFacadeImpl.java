package net.avantic.story.nuevocircuito;

import net.avantic.model.dto.UsuarioDto;
import net.avantic.model.dto.factory.UsuarioDtoFactory;
import net.avantic.model.entity.Usuario;
import net.avantic.service.CircuitoService;
import net.avantic.service.UsuarioService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
public class NuevoCircuitoFacadeImpl implements NuevoCircuitoFacade {

    @Inject
    UsuarioService usuarioService;

    @Inject
    CircuitoService circuitoService;

    @Override
    public void execute(NuevoCircuitoCommand command) {
        String nombreCircuito = command.getNombre();

        List<Usuario> usuarios = command.getIdsUsuarios().stream()
                .map(usuarioService::findUsuarioById)
                .map(Optional::get)
                .collect(Collectors.toList());


        circuitoService.crearCircuito(nombreCircuito, usuarios);
    }

    @Override
    public List<UsuarioDto> listUsuarios() {
        return usuarioService.listarUsuarios().stream()
                .map(UsuarioDtoFactory::newDto)
                .collect(Collectors.toList());
    }
}
