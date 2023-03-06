package net.avantic.model.dao.hibernate;

import net.avantic.model.dao.TareaOmitidaDao;
import net.avantic.model.entity.Tarea;
import net.avantic.model.entity.TareaOmitida;

import javax.enterprise.context.ApplicationScoped;
import java.util.Optional;

@ApplicationScoped
public class TareaOmitidaDaoHibernateImpl implements TareaOmitidaDao {

    @SuppressWarnings("unchecked")
    @Override
    public Optional<TareaOmitida> findByTarea(Tarea tarea) {
        return TareaOmitida.getEntityManager()
                .createQuery("SELECT o FROM TareaOmitida o WHERE o.tarea = :tarea")
                .setParameter("tarea", tarea)
                .getResultList()
                .stream()
                .findFirst();
    }
}
