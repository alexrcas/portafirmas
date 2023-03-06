package net.avantic.model.dao;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import net.avantic.model.entity.Usuario;
import net.avantic.model.entity.Sustitucion;

import java.util.List;

public interface SustitucionDao extends PanacheRepository<Sustitucion> {

    List<Sustitucion> listByUsuario(Usuario usuario);
}
