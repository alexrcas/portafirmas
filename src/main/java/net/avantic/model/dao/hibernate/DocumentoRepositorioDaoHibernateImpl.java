package net.avantic.model.dao.hibernate;

import net.avantic.model.dao.DocumentoRepositorioDao;
import net.avantic.model.entity.DocumentoRepositorio;
import net.avantic.type.UuidDocumento;

import javax.enterprise.context.ApplicationScoped;
import java.util.Optional;

@ApplicationScoped
public class DocumentoRepositorioDaoHibernateImpl implements DocumentoRepositorioDao {

    @Override
    public Optional<DocumentoRepositorio> findByUuidDocumento(UuidDocumento uuidDocumento) {
        String uuid = uuidDocumento.getUuid().toString();
        return find("uriDocumento", uuid).firstResultOptional();
    }
}
