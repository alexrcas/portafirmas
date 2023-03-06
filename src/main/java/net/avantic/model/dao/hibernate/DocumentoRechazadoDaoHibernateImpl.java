package net.avantic.model.dao.hibernate;

import net.avantic.model.dao.DocumentoRechazadoDao;
import net.avantic.model.entity.DocumentoFirmado;
import net.avantic.model.entity.DocumentoRechazado;
import net.avantic.model.entity.Usuario;
import net.avantic.model.entity.Tarea;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class DocumentoRechazadoDaoHibernateImpl implements DocumentoRechazadoDao {

    @SuppressWarnings("unchecked")
    @Override
    public Optional<DocumentoRechazado> findByTarea(Tarea tarea) {
        return DocumentoRechazado.getEntityManager()
                        .createQuery("SELECT d FROM DocumentoRechazado d WHERE d.tarea = :tarea")
                        .setParameter("tarea", tarea)
                        .getResultList()
                        .stream()
                        .findFirst();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<DocumentoRechazado> listByUsuario(Usuario usuario) {
        return DocumentoFirmado.getEntityManager()
                .createQuery("SELECT d FROM DocumentoRechazado d WHERE d.tarea.usuario = :usuario")
                .setParameter("usuario", usuario)
                .getResultList();
    }
}
