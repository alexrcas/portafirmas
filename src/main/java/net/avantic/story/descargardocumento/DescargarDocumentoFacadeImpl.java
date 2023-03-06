package net.avantic.story.descargardocumento;

import net.avantic.service.RepositorioService;
import net.avantic.type.UuidDocumento;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.InputStream;
import java.util.UUID;

@ApplicationScoped
public class DescargarDocumentoFacadeImpl implements DescargarDocumentoFacade {

    @Inject
    RepositorioService repositorioService;

    @Override
    public InputStream descargarContenidoDocumento(String uuid) {
        UuidDocumento uuidDocumento = new UuidDocumento(UUID.fromString(uuid));
        return repositorioService.descargarContenidoDocumentoByUuid(uuidDocumento);
    }
}
