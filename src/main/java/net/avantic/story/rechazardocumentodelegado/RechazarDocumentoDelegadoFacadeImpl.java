package net.avantic.story.rechazardocumentodelegado;

import net.avantic.model.entity.Usuario;
import net.avantic.model.entity.Tarea;
import net.avantic.service.TareaService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class RechazarDocumentoDelegadoFacadeImpl implements RechazarDocumentoDelegadoFacade {

    @Inject
    TareaService tareaService;

    @Override
    public void execute(RechazarDocumentoDelegadoCommand command) {
        Tarea tarea = tareaService.getTarea(command.getIdTarea());
        tareaService.rechazarTareaDelegado(tarea, command.getMotivo());
    }

    @Override
    public Usuario getUsuarioAusente(Long idTarea) {
        return tareaService.getTarea(idTarea).getUsuario();
    }
}
