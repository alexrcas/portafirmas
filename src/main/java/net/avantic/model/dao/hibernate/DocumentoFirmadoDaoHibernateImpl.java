package net.avantic.model.dao.hibernate;

import net.avantic.model.dao.DocumentoFirmadoDao;
import net.avantic.model.entity.DocumentoFirmado;
import net.avantic.model.entity.Usuario;
import net.avantic.model.entity.Tarea;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class DocumentoFirmadoDaoHibernateImpl implements DocumentoFirmadoDao {

    @SuppressWarnings("unchecked")
    @Override
    public Optional<DocumentoFirmado> findByTarea(Tarea tarea) {
        return DocumentoFirmado.getEntityManager()
                        .createQuery("SELECT d FROM DocumentoFirmado d WHERE d.tareaFirma = :tarea")
                        .setParameter("tarea", tarea)
                        .getResultList()
                        .stream()
                        .findFirst();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<DocumentoFirmado> listByUsuario(Usuario usuario) {
        return DocumentoFirmado.getEntityManager()
                .createQuery("SELECT d FROM DocumentoFirmado d WHERE d.tareaFirma.usuario = :usuario")
                .setParameter("usuario", usuario)
                .getResultList();
    }
}
