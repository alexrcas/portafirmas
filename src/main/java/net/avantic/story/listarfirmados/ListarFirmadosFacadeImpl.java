package net.avantic.story.listarfirmados;

import net.avantic.model.dto.LineaDocumentoFirmadoDto;
import net.avantic.model.entity.DocumentoFirmado;
import net.avantic.model.entity.Usuario;
import net.avantic.service.SecurityService;
import net.avantic.service.TareaService;
import net.avantic.model.dto.factory.LineaDocumentoFirmadoDtoFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@ApplicationScoped
public class ListarFirmadosFacadeImpl implements ListarFirmadosFacade {

    @Inject
    TareaService tareaService;

    @Inject
    LineaDocumentoFirmadoDtoFactory lineaDocumentoFirmadoDtoFactory;

    @Inject
    SecurityService securityService;

    @Override
    public List<LineaDocumentoFirmadoDto> listarDocumentosFirmados() {
        Usuario usuario = securityService.getUsuarioActivo();

        List<DocumentoFirmado> firmadosUsuario = tareaService.listDocumentosFirmados(usuario);

        List<DocumentoFirmado> firmadosComoSustituto = tareaService.listDocumentosFirmadosComoSustituto(usuario);

        return Stream.of(firmadosUsuario, firmadosComoSustituto)
                .flatMap(List::stream)
                .map(lineaDocumentoFirmadoDtoFactory::newInstance)
                .collect(Collectors.toList());
    }
}
