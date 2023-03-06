package net.avantic.story.nuevaausencia;

import net.avantic.model.dto.UsuarioDto;
import net.avantic.model.dto.factory.UsuarioDtoFactory;
import net.avantic.model.entity.Usuario;
import net.avantic.service.AusenciaService;
import net.avantic.service.UsuarioService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class NuevaAusenciaFacadeImpl implements NuevaAusenciaFacade {

    @Inject
    UsuarioService usuarioService;

    @Inject
    AusenciaService ausenciaService;

    @Override
    public void execute(NuevaAusenciaCommand command) {
        Usuario usuarioAusente = usuarioService.findUsuarioById(command.getIdAusente()).get();
        Usuario usuarioSustituto = usuarioService.findUsuarioById(command.getIdSustituto()).get();
        ausenciaService.crearAusencia(usuarioAusente, usuarioSustituto);
    }

    @Override
    public List<UsuarioDto> listarUsuariosDisponibles() {
        return usuarioService.listarUsuariosNoAusentes().stream()
                .map(UsuarioDtoFactory::newDto)
                .collect(Collectors.toList());
    }
}
