package net.avantic.story.listarpendientes;

import net.avantic.model.dto.LineaDocumentoPendienteDto;
import net.avantic.model.dto.factory.LineaDocumentoPendienteDtoFactory;
import net.avantic.model.entity.Usuario;
import net.avantic.model.entity.Tarea;
import net.avantic.service.SecurityService;
import net.avantic.service.TareaService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@ApplicationScoped
public class ListarPendientesFacadeImpl implements ListarPendientesFacade {

    @Inject
    TareaService tareaService;

    @Inject
    LineaDocumentoPendienteDtoFactory lineaDocumentoPendienteDtoFactory;

    @Inject
    SecurityService securityService;

    @Override
    public List<LineaDocumentoPendienteDto> listarDocumentosPendientes() {
        Usuario usuario = securityService.getUsuarioActivo();

        List<Tarea> tareasUsuario = tareaService.listTareasPendientesUsuario(usuario).stream()
                .filter(t -> tareaService.isDisponible(t, usuario))
                .collect(Collectors.toList());

        List<Tarea> tareasSustituto = tareaService.listTareasPendientesSustituto(usuario);

        List<Tarea> tareasDelegadas = tareaService.listTareasDelegadasPendientes(usuario).stream()
                .filter(tarea -> tareaService.isDisponible(tarea, tarea.getUsuario()))
                .collect(Collectors.toList());

        return Stream.of(tareasUsuario, tareasSustituto, tareasDelegadas)
                .flatMap(List::stream)
                .map(lineaDocumentoPendienteDtoFactory::newInstance)
                .collect(Collectors.toList());
    }
}
