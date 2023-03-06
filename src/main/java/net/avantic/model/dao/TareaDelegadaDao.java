package net.avantic.model.dao;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import net.avantic.model.entity.Usuario;
import net.avantic.model.entity.TareaDelegada;
import net.avantic.model.entity.Tarea;

import java.util.List;
import java.util.Optional;

public interface TareaDelegadaDao extends PanacheRepository<TareaDelegada> {

    List<TareaDelegada> listPendientesByUsuario(Usuario usuario);

    Optional<TareaDelegada> findByTarea(Tarea tarea);
}
