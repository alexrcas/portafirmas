package net.avantic.model.dto;

import java.util.List;

public class InformacionCircuitoDto {

    List<InformacionTareaDto> infoTareasDto;

    public InformacionCircuitoDto(List<InformacionTareaDto> infoTareasDto) {
        this.infoTareasDto = infoTareasDto;
    }

    public List<InformacionTareaDto> getInfoTareasDto() {
        return infoTareasDto;
    }
}
