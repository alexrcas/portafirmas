package net.avantic.model.dao.hibernate;

import net.avantic.model.dao.TareaDao;
import net.avantic.model.entity.DocumentoRepositorio;
import net.avantic.model.entity.Usuario;
import net.avantic.model.entity.GrupoTareaCircuito;
import net.avantic.model.entity.Tarea;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class TareaDaoHibernateImpl implements TareaDao {

    @Override
    public Tarea get(Long id) {
        return Tarea.findById(id);
    }

    @Override
    public void save(Tarea tarea) {
        getEntityManager().persist(tarea);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Tarea> listPendientes(Usuario usuario) {
        return  getEntityManager()
                    .createQuery("SELECT t FROM Tarea t WHERE t NOT IN (SELECT d.tarea FROM DocumentoRechazado d) " +
                            "AND t NOT in (SELECT df.tareaFirma FROM DocumentoFirmado df) " +
                            "AND t NOT in (SELECT ot.tarea FROM TareaOmitida ot) " +
                            "AND t not in (SELECT dv.tareaValidacion FROM DocumentoValidado dv)" +
                            "AND t.usuario.dni = :user")
                    .setParameter("user", usuario.getDni())
                    .getResultList();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Tarea> listPendientesYOmitidas(Usuario usuario) {
        return getEntityManager()
                    .createQuery("SELECT t FROM Tarea t WHERE t NOT IN (SELECT d.tarea FROM DocumentoRechazado d) " +
                            "AND t NOT in (SELECT df.tareaFirma FROM DocumentoFirmado df) " +
                            "AND t NOT in (SELECT dv.tareaValidacion FROM DocumentoValidado dv)" +
                            "AND t.usuario.dni = :user")
                    .setParameter("user", usuario.getDni())
                    .getResultList();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Tarea> listMismoGrupo(GrupoTareaCircuito grupoTareaCircuito) {
        return getEntityManager()
                    .createQuery("SELECT p.tarea FROM PertenenciaTareaCircuito p WHERE p.grupoTareaCircuito = :grupoTareaCircuito")
                    .setParameter("grupoTareaCircuito", grupoTareaCircuito)
                    .getResultList();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Tarea> findPendienteFirmaByDocumento(DocumentoRepositorio documentoRepositorio) {
        return getEntityManager()
                .createQuery("SELECT t FROM Tarea t WHERE t.documentoRepositorio.uriDocumento = :uriDocumento " +
                        "AND t NOT IN (SELECT dr.tarea FROM DocumentoRechazado dr) " +
                        "AND t NOT in (SELECT df.tareaFirma FROM DocumentoFirmado df) " +
                        "AND t not in (SELECT ot.tarea FROM TareaOmitida ot)")
                .setParameter("uriDocumento", documentoRepositorio.getUriDocumento())
                .getResultList();
    }
}
