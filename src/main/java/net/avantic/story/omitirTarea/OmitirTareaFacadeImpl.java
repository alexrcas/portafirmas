package net.avantic.story.omitirTarea;

import net.avantic.model.entity.Tarea;
import net.avantic.service.TareaService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Optional;

@ApplicationScoped
public class OmitirTareaFacadeImpl implements OmitirTareaFacade {

    @Inject
    TareaService tareaService;

    @Override
    public void execute(OmitirTareaCommand command) {
        Optional<Tarea> tareaOpt = Tarea.findByIdOptional(command.getIdTarea());
        tareaService.omitirTarea(tareaOpt.get());
    }
}
