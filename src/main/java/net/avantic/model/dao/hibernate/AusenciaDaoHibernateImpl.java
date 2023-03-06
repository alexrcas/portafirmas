package net.avantic.model.dao.hibernate;

import net.avantic.model.dao.AusenciaDao;
import net.avantic.model.entity.Ausencia;
import net.avantic.model.entity.Sustitucion;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class AusenciaDaoHibernateImpl implements AusenciaDao {

    @Override
    public List<Ausencia> listBySustitucion(Sustitucion sustitucion) {
        return Ausencia.find("idSustitucion", sustitucion)
                .list();
    }
}
