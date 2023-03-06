package net.avantic.model.dao;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import net.avantic.model.entity.DocumentoFirmado;
import net.avantic.model.entity.DocumentoValidado;
import net.avantic.model.entity.Tarea;
import net.avantic.model.entity.Usuario;

import java.util.List;
import java.util.Optional;

public interface DocumentoValidadoDao extends PanacheRepository<DocumentoValidado> {

    Optional<DocumentoValidado> findByTarea(Tarea tarea);
}
