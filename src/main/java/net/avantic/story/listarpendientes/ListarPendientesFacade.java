package net.avantic.story.listarpendientes;

import net.avantic.model.dto.LineaDocumentoPendienteDto;

import java.util.List;

public interface ListarPendientesFacade {

    List<LineaDocumentoPendienteDto> listarDocumentosPendientes();
}
