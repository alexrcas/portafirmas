package net.avantic.service;

import net.avantic.model.entity.DocumentoFirmado;
import net.avantic.type.UuidDocumento;

import java.io.InputStream;
import java.util.List;

public interface RepositorioService {

    InputStream descargarContenidoDocumentoByUuid(UuidDocumento uuid);

    String ping();

    void enviarFirmas(List<DocumentoFirmado> documentosFirmados);
}
