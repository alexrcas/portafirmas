package net.avantic.story.rechazardocumentosustituto;

import net.avantic.model.entity.Usuario;
import net.avantic.model.entity.Tarea;
import net.avantic.service.TareaService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class RechazarDocumentoSustitutoFacadeImpl implements RechazarDocumentoSustitutoFacade {

    @Inject
    TareaService tareaService;

    @Override
    public void execute(RechazarDocumentoSustitutoCommand command) {
        Tarea tarea = tareaService.getTarea(command.getIdTarea());
        tareaService.rechazarTareaSustituto(tarea, command.getMotivo());
    }

    @Override
    public Usuario getUsuarioAusente(Long idTarea) {
        return tareaService.getTarea(idTarea).getUsuario();
    }
}
