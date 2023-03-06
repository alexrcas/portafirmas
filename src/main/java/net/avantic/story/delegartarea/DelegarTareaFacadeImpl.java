package net.avantic.story.delegartarea;

import net.avantic.model.dto.UsuarioDto;
import net.avantic.model.dto.factory.UsuarioDtoFactory;
import net.avantic.model.entity.Usuario;
import net.avantic.model.entity.Tarea;
import net.avantic.service.UsuarioService;
import net.avantic.service.TareaService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
public class DelegarTareaFacadeImpl implements DelegarTareaFacade {

    @Inject
    TareaService tareaService;

    @Inject
    UsuarioService usuarioService;

    @Override
    public void execute(DelegarTareaCommand command) {
        Optional<Tarea> tareaOpt = Tarea.findByIdOptional(command.getIdTarea());
        Usuario usuario = Usuario.findById(command.getIdUsuario());
        tareaService.delegarTarea(tareaOpt.get(), usuario);
    }

    @Override
    public List<UsuarioDto> listUsuarios(Long idTarea) {

        Tarea tarea = Tarea.findById(idTarea);
        Usuario usuarioOriginal = tarea.getUsuario();

        return usuarioService.listarUsuariosNoAusentes().stream()
                .filter(f -> f.getId().longValue() != usuarioOriginal.getId().longValue())
                .map(UsuarioDtoFactory::newDto)
                .collect(Collectors.toList());
    }
}
