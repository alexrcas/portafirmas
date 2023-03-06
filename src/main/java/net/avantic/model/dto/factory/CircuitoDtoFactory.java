package net.avantic.model.dto.factory;

import net.avantic.model.dto.CircuitoDto;
import net.avantic.model.entity.Circuito;

import javax.enterprise.context.ApplicationScoped;
import java.util.Optional;


@ApplicationScoped
public class CircuitoDtoFactory {

    public CircuitoDto newDto(Circuito circuito) {
        return new CircuitoDto(circuito.getId(), circuito.getNombre());
    }

    public CircuitoDto newDtoNullSafe(Optional<Circuito> circuitoOpt) {
        if (circuitoOpt.isEmpty()) {
            return new CircuitoDto(null, "");
        }

        return new CircuitoDto(circuitoOpt.get().getId(), circuitoOpt.get().getNombre());
    }
}
