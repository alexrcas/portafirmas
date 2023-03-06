package net.avantic.story.listarfirmados;

import net.avantic.model.dto.LineaDocumentoFirmadoDto;

import java.util.List;

public interface ListarFirmadosFacade {

    List<LineaDocumentoFirmadoDto> listarDocumentosFirmados();
}
