package net.avantic.model.dao.hibernate;

import net.avantic.model.dao.UsuarioDao;
import net.avantic.model.entity.Circuito;
import net.avantic.model.entity.Usuario;
import net.avantic.model.entity.PertenenciaUsuarioCircuito;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class UsuarioDaoHibernateImpl implements UsuarioDao {

    @Override
    public List<Usuario> listByCircuito(Circuito circuito) {
        List<PertenenciaUsuarioCircuito> pertenencias = PertenenciaUsuarioCircuito.list("idCircuito", circuito);
        return pertenencias.stream()
                .map(PertenenciaUsuarioCircuito::getUsuario)
                .collect(Collectors.toList());
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Usuario> listNoAusentes() {
        return Usuario.getEntityManager()
                .createQuery("SELECT f FROM Usuario f WHERE f NOT IN (SELECT a.usuario FROM Ausente a)")
                .getResultList();
    }

    @Override
    public Usuario findByDni(String dni) {
        return Usuario.find("dni", dni).firstResult();
    }
}
