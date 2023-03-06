package net.avantic.story.listarcircuitos;

import com.ctc.wstx.shaded.msv_core.util.Uri;
import net.avantic.model.dto.CircuitoDto;
import net.avantic.model.dto.UsuarioDto;
import net.avantic.model.dto.factory.CircuitoDtoFactory;
import net.avantic.model.dto.factory.UsuarioDtoFactory;
import net.avantic.model.entity.Circuito;
import net.avantic.service.CircuitoService;
import net.avantic.service.UsuarioService;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
public class ListarCircuitosFacadeImpl implements ListarCircuitosFacade {

    @Inject
    UsuarioService usuarioService;

    @Inject
    CircuitoService circuitoService;

    @Inject
    CircuitoDtoFactory circuitoDtoFactory;

    @Override
    @RolesAllowed("ADMIN")
    public List<UsuarioDto> listarUsuarios() {
        return usuarioService.listarUsuarios().stream()
                .map(UsuarioDtoFactory::newDto)
                .collect(Collectors.toList());
    }

    @Override
    @RolesAllowed("ADMIN")
    public List<CircuitoDto> listarCircuitos() {
        return circuitoService.listarCircuitos().stream()
                .map(circuitoDtoFactory::newDto)
                .collect(Collectors.toList());
    }

    @Override
    @RolesAllowed("ADMIN")
    public URI routingRedirect() {
        try {
            List<Circuito> circuitos = circuitoService.listarCircuitos();
            if (circuitos.isEmpty()) {
                URI uri = new URI("/secured/listarCircuitos/crear" );
                return uri;
            }

            Long id = circuitos.get(0).getId();
            URI uri = new URI("/secured/listarCircuitos/" + id);
            return uri;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    @RolesAllowed("ADMIN")
    public URI crearRoutingRedirect() {
        try {
            List<Circuito> circuitos = circuitoService.listarCircuitos();
            if (circuitos.isEmpty()) {
                URI uri = new URI("/secured/listarCircuitos/crear" );
                return uri;
            }

            Long id = circuitos.get(0).getId();
            URI uri = new URI("/secured/listarCircuitos/" + id);
            return uri;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    @RolesAllowed("ADMIN")
    public List<UsuarioDto> listarUsuariosByCircuito(Long id) {
        Circuito circuito = circuitoService.findCircuitoById(id).get();
        return usuarioService.listarUsuariosByCircuito(circuito).stream()
                .map(UsuarioDtoFactory::newDto)
                .collect(Collectors.toList());
    }
}
