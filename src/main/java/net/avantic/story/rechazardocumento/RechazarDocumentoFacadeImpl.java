package net.avantic.story.rechazardocumento;

import net.avantic.model.entity.Tarea;
import net.avantic.service.TareaService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Optional;

@ApplicationScoped
public class RechazarDocumentoFacadeImpl implements RechazarDocumentoFacade {

    @Inject
    TareaService tareaService;

    @Override
    public void execute(RechazarDocumentoCommand command) {
        Optional<Tarea> tareaOpt = Tarea.findByIdOptional(command.getIdTarea());
        tareaService.rechazarTarea(tareaOpt.get(), command.getMotivo());
    }
}
