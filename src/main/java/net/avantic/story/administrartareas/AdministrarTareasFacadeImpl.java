package net.avantic.story.administrartareas;

import net.avantic.model.dto.UsuarioDto;
import net.avantic.model.dto.LineaAdministrarDocumentoDto;
import net.avantic.model.dto.factory.UsuarioDtoFactory;
import net.avantic.model.dto.factory.LineaAdministrarDocumentoDtoFactory;
import net.avantic.model.entity.Usuario;
import net.avantic.service.UsuarioService;
import net.avantic.service.TareaService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class AdministrarTareasFacadeImpl implements AdministrarTareasFacade {

    @Inject
    UsuarioService usuarioService;

    @Inject
    TareaService tareaService;

    @Inject
    LineaAdministrarDocumentoDtoFactory lineaAdministrarDocumentoDtoFactory;

    @Override
    public List<UsuarioDto> listUsuarios() {
        return usuarioService.listarUsuarios().stream()
                .map(UsuarioDtoFactory::newDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<LineaAdministrarDocumentoDto> listarTareas(Long idUsuario) {
        Usuario usuario = Usuario.findById(idUsuario);

        return tareaService.listTareasPendientesYOmitidasUsuario(usuario).stream()
                .map(lineaAdministrarDocumentoDtoFactory::newInstance)
                .collect(Collectors.toList());
    }
}
