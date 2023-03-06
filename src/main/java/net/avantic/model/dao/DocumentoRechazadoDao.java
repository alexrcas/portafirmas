package net.avantic.model.dao;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import net.avantic.model.entity.DocumentoRechazado;
import net.avantic.model.entity.Usuario;
import net.avantic.model.entity.Tarea;

import java.util.List;
import java.util.Optional;

public interface DocumentoRechazadoDao extends PanacheRepository<DocumentoRechazado> {

    Optional<DocumentoRechazado> findByTarea(Tarea tarea);

    List<DocumentoRechazado> listByUsuario(Usuario usuario);
}
