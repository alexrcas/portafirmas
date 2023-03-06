package net.avantic.model.dao;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import net.avantic.model.entity.Circuito;
import net.avantic.model.entity.Usuario;

import java.util.List;

public interface UsuarioDao extends PanacheRepository<Usuario> {

    List<Usuario> listByCircuito(Circuito circuito);

    List<Usuario> listNoAusentes();

    Usuario findByDni(String dni);
}
