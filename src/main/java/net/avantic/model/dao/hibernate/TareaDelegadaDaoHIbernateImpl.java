package net.avantic.model.dao.hibernate;

import net.avantic.model.dao.TareaDelegadaDao;
import net.avantic.model.entity.Usuario;
import net.avantic.model.entity.TareaDelegada;
import net.avantic.model.entity.Tarea;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class TareaDelegadaDaoHIbernateImpl implements TareaDelegadaDao {

    @SuppressWarnings("unchecked")
    @Override
    public List<TareaDelegada> listPendientesByUsuario(Usuario usuario) {
        return TareaDelegada.getEntityManager()
                .createQuery("SELECT t FROM TareaDelegada t WHERE t.usuario = :usuario " +
                        "AND t.tarea NOT IN (SELECT d.tareaFirma FROM DocumentoFirmado d) " +
                        "AND t.tarea NOT IN (SELECT r.tarea FROM DocumentoRechazado r)" )
                .setParameter("usuario", usuario)
                .getResultList();
    }

    @Override
    public Optional<TareaDelegada> findByTarea(Tarea tarea) {
        return TareaDelegada.find("idTarea", tarea)
                .firstResultOptional();
    }
}
