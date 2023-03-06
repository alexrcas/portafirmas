package net.avantic.model.dao;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import net.avantic.model.entity.Tarea;
import net.avantic.model.entity.TareaOmitida;

import java.util.Optional;

public interface TareaOmitidaDao extends PanacheRepository<TareaOmitida> {

    Optional<TareaOmitida> findByTarea(Tarea tarea);
}
