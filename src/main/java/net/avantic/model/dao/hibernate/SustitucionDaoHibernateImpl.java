package net.avantic.model.dao.hibernate;

import net.avantic.model.dao.SustitucionDao;
import net.avantic.model.entity.Usuario;
import net.avantic.model.entity.Sustitucion;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class SustitucionDaoHibernateImpl implements SustitucionDao {

    @Override
    public List<Sustitucion> listByUsuario(Usuario usuario) {
        return Sustitucion.list("idUsuario", usuario);
    }
}
