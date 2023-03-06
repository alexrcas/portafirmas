package net.avantic.model.dao;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import net.avantic.model.entity.Circuito;
import net.avantic.model.entity.PertenenciaUsuarioCircuito;
import net.avantic.model.entity.Usuario;

import java.util.List;
import java.util.Optional;

public interface PertenenciaUsuarioCircuitoDao extends PanacheRepository<PertenenciaUsuarioCircuito> {

    List<PertenenciaUsuarioCircuito> listByCircuito(Circuito circuito);

    Optional<PertenenciaUsuarioCircuito> findByUsuario(Usuario usuario);
}
