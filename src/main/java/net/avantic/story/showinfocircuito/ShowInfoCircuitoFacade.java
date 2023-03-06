package net.avantic.story.showinfocircuito;

import net.avantic.model.dto.InformacionCircuitoDto;

public interface ShowInfoCircuitoFacade {

    InformacionCircuitoDto findInformacionCircuito(Long id);
}
