package net.avantic.model.dao.hibernate;

import net.avantic.model.dao.FirmaSustitutoDao;
import net.avantic.model.entity.FirmaSustituto;
import net.avantic.model.entity.Usuario;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class FirmaSustitutoDaoHibernateImpl implements FirmaSustitutoDao {

    @SuppressWarnings("unchecked")
    @Override
    public List<FirmaSustituto> listByUsuario(Usuario usuario) {
        return FirmaSustituto.getEntityManager()
                .createQuery("SELECT s FROM FirmaSustituto s WHERE s.usuario = :usuario AND s.documentoFirmado.tareaFirma NOT IN (SELECT d.tarea FROM DocumentoRechazado d)")
                .setParameter("usuario", usuario)
                .getResultList();
    }
}
