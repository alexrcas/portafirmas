package net.avantic.model.dao;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import net.avantic.model.entity.DocumentoFirmado;
import net.avantic.model.entity.Usuario;
import net.avantic.model.entity.Tarea;

import java.util.List;
import java.util.Optional;

public interface DocumentoFirmadoDao extends PanacheRepository<DocumentoFirmado> {

    Optional<DocumentoFirmado> findByTarea(Tarea tarea);

    List<DocumentoFirmado> listByUsuario(Usuario usuario);
}
