package net.avantic.model.dto.factory;

import net.avantic.model.dto.CircuitoDto;
import net.avantic.model.dto.UsuarioDto;
import net.avantic.model.entity.*;
import net.avantic.model.dto.LineaDocumentoRechazadoDto;
import net.avantic.service.CircuitoService;
import net.avantic.service.SecurityService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Optional;

@ApplicationScoped
public class LineaDocumentoRechazadoDtoFactory {

    @Inject
    SecurityService securityService;

    @Inject
    CircuitoService circuitoService;

    @Inject
    CircuitoDtoFactory circuitoDtoFactory;

    public LineaDocumentoRechazadoDto newInstance(DocumentoRechazado documentoRechazado) {
        Tarea tarea = documentoRechazado.getTarea();
        Usuario usuario = documentoRechazado.getAutorRechazo();

        UsuarioDto usuarioDto = UsuarioDtoFactory.newDto(usuario);

        UsuarioDto sustituto = RechazoSustituto.find("idDocumentoRechazado", documentoRechazado).firstResultOptional()
                .map(pe -> (RechazoSustituto) pe)
                .map(RechazoSustituto::getUsuario)
                .map(UsuarioDtoFactory::newDto)
                .orElseGet(() -> null);

        boolean isSustituto = false;
        if (sustituto != null) {
            isSustituto = true;
        }

        Optional<Circuito> circuitoOpt = circuitoService.findByTarea(tarea);
        boolean isInCircuito = circuitoOpt.isPresent();

        CircuitoDto circuitoDto = circuitoDtoFactory.newDtoNullSafe(circuitoOpt);

        DocumentoRepositorio documentoRepositorio = tarea.getDocumentoRepositorio();
        return new LineaDocumentoRechazadoDto(tarea.getId(), documentoRepositorio.getNombreDocumento(), documentoRepositorio.getUriDocumento(),
                documentoRepositorio.getDescripcionDocumento(), usuarioDto, sustituto, documentoRechazado.getMotivo(),
                isInCircuito, circuitoDto, isSustituto);
    }
}
