package net.avantic.model.dao.hibernate;

import net.avantic.model.dao.PertenenciaTareaCircuitoDao;
import net.avantic.model.entity.PertenenciaTareaCircuito;
import net.avantic.model.entity.Tarea;

import javax.enterprise.context.ApplicationScoped;
import java.util.Optional;

@ApplicationScoped
public class PertenenciaTareaCircuitoDaoHibernateImpl implements PertenenciaTareaCircuitoDao {

    @Override
    public Optional<PertenenciaTareaCircuito> findByTarea(Tarea tarea) {
        return PertenenciaTareaCircuito.find("idTarea", tarea)
                .firstResultOptional();
    }

}
