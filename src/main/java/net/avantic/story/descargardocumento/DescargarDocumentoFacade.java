package net.avantic.story.descargardocumento;

import java.io.InputStream;

public interface DescargarDocumentoFacade {

    InputStream descargarContenidoDocumento(String uuid);
}
