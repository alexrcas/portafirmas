package net.avantic.story.api;

import net.avantic.type.request.DocumentoRepositorioCircuitoRequest;
import net.avantic.type.request.DocumentoRepositorioRequest;

public interface ApiService {

    String registrarDocumento(DocumentoRepositorioRequest request);

    String enviarACircuito(DocumentoRepositorioCircuitoRequest request);

}
