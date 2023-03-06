package net.avantic.model.dao;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import net.avantic.model.entity.PertenenciaTareaCircuito;
import net.avantic.model.entity.Tarea;

import java.util.Optional;

public interface PertenenciaTareaCircuitoDao extends PanacheRepository<PertenenciaTareaCircuito> {

    Optional<PertenenciaTareaCircuito> findByTarea(Tarea tarea);
}
