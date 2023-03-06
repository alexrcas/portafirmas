package net.avantic.model.dao.hibernate;

import net.avantic.model.dao.DocumentoFirmadoDao;
import net.avantic.model.dao.DocumentoValidadoDao;
import net.avantic.model.entity.DocumentoFirmado;
import net.avantic.model.entity.DocumentoValidado;
import net.avantic.model.entity.Tarea;
import net.avantic.model.entity.Usuario;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class DocumentoValidadoDaoHibernateImpl implements DocumentoValidadoDao {

    @SuppressWarnings("unchecked")
    @Override
    public Optional<DocumentoValidado> findByTarea(Tarea tarea) {
        return DocumentoValidado.getEntityManager()
                .createQuery("SELECT d FROM DocumentoValidado d WHERE d.tareaValidacion = :tarea")
                .setParameter("tarea", tarea)
                .getResultList()
                .stream()
                .findFirst();
    }
}
