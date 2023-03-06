package net.avantic.story.listarpendientescircuito;

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
public class ListarPendientesCircuitoFacadeImpl implements ListarPendientesCircuitoFacade {

    @Inject
    TareaService tareaService;

    @Inject
    LineaDocumentoPendienteDtoFactory lineaDocumentoPendienteDtoFactory;

    @Inject
    SecurityService securityService;

    @Override
    public List<LineaDocumentoPendienteDto> listarDocumentosPendientesCircuito() {
        Usuario usuario = securityService.getUsuarioActivo();
        List<Tarea> tareasUsuario = tareaService.listTareasPendientesUsuario(usuario).stream()
                .filter(tarea -> !tareaService.isDisponible(tarea, usuario))
                .collect(Collectors.toList());

        List<Tarea> tareasSustituto = tareaService.listTareasFirmasEnCircuitoSustituto(usuario);

        return Stream.of(tareasUsuario, tareasSustituto)
                .flatMap(List::stream)
                .map(lineaDocumentoPendienteDtoFactory::newInstance)
                .collect(Collectors.toList());
    }
}
