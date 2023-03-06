package net.avantic.model.dao;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import net.avantic.model.entity.Circuito;
import net.avantic.model.entity.Tarea;

import java.util.List;
import java.util.Optional;

public interface CircuitoDao extends PanacheRepository<Circuito> {

    List<Circuito> listarCircuitos();

    Optional<Circuito> findCircuitoById(Long id);

    Optional<Circuito> findByTarea(Tarea tarea);

}
