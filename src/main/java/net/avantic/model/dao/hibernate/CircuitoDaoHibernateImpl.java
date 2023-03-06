package net.avantic.model.dao.hibernate;

import net.avantic.model.dao.CircuitoDao;
import net.avantic.model.entity.Circuito;
import net.avantic.model.entity.PertenenciaTareaCircuito;
import net.avantic.model.entity.Tarea;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class CircuitoDaoHibernateImpl implements CircuitoDao {

    @Override
    public List<Circuito> listarCircuitos() {
        return listAll();
    }

    @Override
    public Optional<Circuito> findCircuitoById(Long id) {
        return Circuito.findByIdOptional(id);
    }

    @Override
    public Optional<Circuito> findByTarea(Tarea tarea) {
        Optional<PertenenciaTareaCircuito> pertenenciaTareaCircuito = PertenenciaTareaCircuito.find("idTarea", tarea).firstResultOptional();
        return pertenenciaTareaCircuito.map(PertenenciaTareaCircuito::getCircuito);
    }
}
