package net.avantic.story.showinfocircuito;

import net.avantic.model.dto.InformacionCircuitoDto;
import net.avantic.model.dto.factory.InformacionCircuitoDtoFactory;
import net.avantic.model.entity.Tarea;
import net.avantic.service.TareaService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class ShowInfoCircuitoFacadeImpl implements ShowInfoCircuitoFacade {

    @Inject
    InformacionCircuitoDtoFactory informacionCircuitoDtoFactory;

    @Inject
    TareaService tareaService;

    @Override
    public InformacionCircuitoDto findInformacionCircuito(Long id) {
        Tarea tarea = tareaService.getTarea(id);
        List<Tarea> tareas = tareaService.listTareasMismoGrupo(tarea);
        return informacionCircuitoDtoFactory.newDto(tareas);
    }
}
