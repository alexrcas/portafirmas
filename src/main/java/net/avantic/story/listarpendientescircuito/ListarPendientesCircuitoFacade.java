package net.avantic.story.listarpendientescircuito;

import net.avantic.model.dto.LineaDocumentoPendienteDto;

import java.util.List;

public interface ListarPendientesCircuitoFacade {

    List<LineaDocumentoPendienteDto> listarDocumentosPendientesCircuito();
}
