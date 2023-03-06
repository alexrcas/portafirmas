package net.avantic.story.listarausencias;

import net.avantic.model.dto.AusenciaDto;
import net.avantic.model.dto.UsuarioDto;
import net.avantic.model.dto.factory.AusenciaDtoFactory;
import net.avantic.model.dto.factory.UsuarioDtoFactory;
import net.avantic.service.AusenciaService;
import net.avantic.service.UsuarioService;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class ListarAusenciasFacadeImpl implements ListarAusenciasFacade {

    @Inject
    AusenciaService ausenciaService;

    @Inject
    AusenciaDtoFactory ausenciaDtoFactory;

    @Override
    @RolesAllowed("ADMIN")
    public List<AusenciaDto> listarAusencias() {
        return ausenciaService.list().stream()
                .map(ausenciaDtoFactory::newDto)
                .collect(Collectors.toList());
    }
}
