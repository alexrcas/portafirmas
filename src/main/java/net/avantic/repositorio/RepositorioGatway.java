package net.avantic.repositorio;

import net.avantic.model.entity.DocumentoFirmado;
import net.avantic.repositorio.dto.DocumentoRepositorioDto;
import net.avantic.type.UuidDocumento;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;

public interface RepositorioGatway {

    String ping();

    Optional<DocumentoRepositorioDto> findDocumentoByUuid(UuidDocumento uuidDocumento);

    InputStream descargarContenidoDocumentoByUuid(UuidDocumento uuidDocumento);

    void enviarFirmas(List<DocumentoFirmado> documentosFirmados);
}
