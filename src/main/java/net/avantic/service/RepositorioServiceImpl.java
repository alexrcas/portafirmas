package net.avantic.service;

import net.avantic.model.entity.DocumentoFirmado;
import net.avantic.repositorio.RepositorioGatway;
import net.avantic.type.UuidDocumento;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.InputStream;
import java.util.List;

@ApplicationScoped
public class RepositorioServiceImpl implements RepositorioService {

    @Inject
    RepositorioGatway repositorioGatway;

    @Override
    public InputStream descargarContenidoDocumentoByUuid(UuidDocumento uuid) {
        return repositorioGatway.descargarContenidoDocumentoByUuid(uuid);
    }

    @Override
    public String ping() {
        return repositorioGatway.ping();
    }

    @Override
    public void enviarFirmas(List<DocumentoFirmado> documentosFirmados) {
        repositorioGatway.enviarFirmas(documentosFirmados);
    }

}
