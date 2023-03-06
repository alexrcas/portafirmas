package net.avantic.model.dao.hibernate;

import net.avantic.model.dao.PertenenciaUsuarioCircuitoDao;
import net.avantic.model.entity.Circuito;
import net.avantic.model.entity.PertenenciaUsuarioCircuito;
import net.avantic.model.entity.Usuario;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class PertenenciaUsuarioCircuitoDaoHibernateImpl implements PertenenciaUsuarioCircuitoDao {

    @Override
    public List<PertenenciaUsuarioCircuito> listByCircuito(Circuito circuito) {
        return PertenenciaUsuarioCircuito.find("idCircuito", circuito)
                .list();
    }

    @Override
    public Optional<PertenenciaUsuarioCircuito> findByUsuario(Usuario usuario) {
        return PertenenciaUsuarioCircuito.find("idUsuario", usuario)
                .firstResultOptional();
    }
}
