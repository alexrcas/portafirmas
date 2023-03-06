package net.avantic.model.dao;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import net.avantic.model.entity.DocumentoRepositorio;
import net.avantic.type.UuidDocumento;

import java.util.Optional;

public interface DocumentoRepositorioDao extends PanacheRepository<DocumentoRepositorio> {

    Optional<DocumentoRepositorio> findByUuidDocumento(UuidDocumento uuidDocumento);
}
