package net.avantic.model.dto.factory;

import net.avantic.model.dto.CircuitoDto;
import net.avantic.model.dto.UsuarioDto;
import net.avantic.model.entity.*;
import net.avantic.model.dto.LineaDocumentoFirmadoDto;
import net.avantic.service.CircuitoService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Optional;

@ApplicationScoped
public class LineaDocumentoFirmadoDtoFactory {

    @Inject
    CircuitoService circuitoService;

    @Inject
    CircuitoDtoFactory circuitoDtoFactory;

    public LineaDocumentoFirmadoDto newInstance(DocumentoFirmado documentoFirmado) {

        Tarea tarea = documentoFirmado.getTareaFirma();

        UsuarioDto sustituto = FirmaSustituto.find("idDocumentoFirmado", documentoFirmado).firstResultOptional()
                .map(pe -> (FirmaSustituto) pe)
                .map(FirmaSustituto::getUsuario)
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

        UsuarioDto usuarioOriginal = UsuarioDtoFactory.newDto(tarea.getUsuario());

        return new LineaDocumentoFirmadoDto(tarea.getId(), documentoRepositorio.getNombreDocumento(),
                documentoRepositorio.getUriDocumento(), documentoRepositorio.getDescripcionDocumento(), isInCircuito, circuitoDto, isSustituto, usuarioOriginal, sustituto);
    }
}
