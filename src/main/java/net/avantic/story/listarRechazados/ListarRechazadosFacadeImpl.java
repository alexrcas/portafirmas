package net.avantic.story.listarRechazados;

import net.avantic.model.dto.LineaDocumentoRechazadoDto;
import net.avantic.model.dto.factory.LineaDocumentoRechazadoDtoFactory;
import net.avantic.model.entity.Usuario;
import net.avantic.service.SecurityService;
import net.avantic.service.TareaService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class ListarRechazadosFacadeImpl implements ListarRechazadosFacade {

    @Inject
    TareaService tareaService;

    @Inject
    LineaDocumentoRechazadoDtoFactory lineaDocumentoRechazadoDtoFactory;

    @Inject
    SecurityService securityService;

    @Override
    public List<LineaDocumentoRechazadoDto> listarDocumentosRechazados() {
        Usuario usuario = securityService.getUsuarioActivo();
        return tareaService.listDocumentosRechazados(usuario).stream()
                .map(lineaDocumentoRechazadoDtoFactory::newInstance)
                .collect(Collectors.toList());
    }
}
