package net.avantic.story.listarcircuitos;

import net.avantic.model.dto.CircuitoDto;
import net.avantic.model.dto.UsuarioDto;

import java.net.URI;
import java.util.List;

public interface ListarCircuitosFacade {

    List<UsuarioDto> listarUsuarios();

    List<CircuitoDto> listarCircuitos();

    URI routingRedirect();

    URI crearRoutingRedirect();

    List<UsuarioDto> listarUsuariosByCircuito(Long id);
}
