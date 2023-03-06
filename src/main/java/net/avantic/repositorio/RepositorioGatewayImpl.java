package net.avantic.repositorio;

import net.avantic.model.entity.DocumentoFirmado;
import net.avantic.repositorio.dto.DocumentoRepositorioDto;
import net.avantic.type.UuidDocumento;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class RepositorioGatewayImpl implements RepositorioGatway {

    @Inject
    RepositorioWebServiceImpl repositorioWebService;

    @Override
    public String ping() {
        return repositorioWebService.ping();
    }

    @Override
    public Optional<DocumentoRepositorioDto> findDocumentoByUuid(UuidDocumento uuidDocumento) {
            return Optional.of(repositorioWebService.findDocumentoByUuid(uuidDocumento));
    }

    @Override
    public InputStream descargarContenidoDocumentoByUuid(UuidDocumento uuidDocumento) {
        return repositorioWebService.descargarContenidoDocumentoByUuid(uuidDocumento);
    }

    @Override
    public void enviarFirmas(List<DocumentoFirmado> documentosFirmados) {
        repositorioWebService.enviarFirmas(documentosFirmados);
    }
}
