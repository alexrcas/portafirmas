package net.avantic.story.listarRechazados;

import net.avantic.model.dto.LineaDocumentoRechazadoDto;

import java.util.List;

public interface ListarRechazadosFacade {

    List<LineaDocumentoRechazadoDto> listarDocumentosRechazados();
}
