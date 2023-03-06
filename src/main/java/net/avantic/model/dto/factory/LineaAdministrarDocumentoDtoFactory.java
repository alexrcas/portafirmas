package net.avantic.model.dto.factory;

import net.avantic.model.dto.CircuitoDto;
import net.avantic.model.dto.LineaAdministrarDocumentoDto;
import net.avantic.model.entity.*;
import net.avantic.service.CircuitoService;
import net.avantic.service.SecurityService;
import net.avantic.service.TareaService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Optional;

@ApplicationScoped
public class LineaAdministrarDocumentoDtoFactory {

    @Inject
    CircuitoService circuitoService;

    @Inject
    SecurityService securityService;

    @Inject
    TareaService tareaService;

    @Inject
    CircuitoDtoFactory circuitoDtoFactory;


    public LineaAdministrarDocumentoDto newInstance(Tarea tarea) {
        boolean isSustituto = false;
        Usuario usuario = securityService.getUsuarioActivo();

        boolean isDelegado = TareaDelegada.find("idTarea", tarea)
                .firstResultOptional()
                .isPresent();

        if (!isDelegado && !usuario.equals(tarea.getUsuario())) {
            isSustituto = true;
        }

        boolean isOmitido = TareaOmitida.find("idTarea", tarea)
                .firstResultOptional()
                .isPresent();

        Optional<Circuito> circuitoOpt = circuitoService.findByTarea(tarea);
        boolean isInCircuito = circuitoOpt.isPresent();

        CircuitoDto circuitoDto = circuitoDtoFactory.newDtoNullSafe(circuitoOpt);

        DocumentoRepositorio documentoRepositorio = tarea.getDocumentoRepositorio();
        return new LineaAdministrarDocumentoDto(tarea.getId(), documentoRepositorio.getNombreDocumento(),
                documentoRepositorio.getUriDocumento(), documentoRepositorio.getDescripcionDocumento(), isInCircuito, circuitoDto, isSustituto, isDelegado, isOmitido);
    }
}
