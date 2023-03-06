package net.avantic.model.dto.factory;

import net.avantic.model.dto.CircuitoDto;
import net.avantic.model.entity.*;
import net.avantic.model.dto.LineaDocumentoPendienteDto;
import net.avantic.model.type.TareaVisitor;
import net.avantic.service.CircuitoService;
import net.avantic.service.SecurityService;
import net.avantic.service.TareaService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Optional;

@ApplicationScoped
public class LineaDocumentoPendienteDtoFactory {

    @Inject
    CircuitoService circuitoService;

    @Inject
    SecurityService securityService;

    @Inject
    CircuitoDtoFactory circuitoDtoFactory;


    public LineaDocumentoPendienteDto newInstance(Tarea tarea) {
        boolean isSustituto = false;
        Usuario usuario = securityService.getUsuarioActivo();

        boolean isDelegado = TareaDelegada.find("idTarea", tarea)
                .firstResultOptional()
                .isPresent();

        boolean isUsuarioOriginal = tarea.getUsuario().getDni().equals(usuario.getDni());

        if (!isDelegado && !usuario.equals(tarea.getUsuario())) {
            isSustituto = true;
        }

        Optional<Circuito> circuitoOpt = circuitoService.findByTarea(tarea);
        boolean isInCircuito = circuitoOpt.isPresent();

        CircuitoDto circuitoDto = circuitoDtoFactory.newDtoNullSafe(circuitoOpt);

        ValidacionVisitor visitor = new ValidacionVisitor();
        boolean isValidacion = visitor.isValidacion(tarea);

        DocumentoRepositorio documentoRepositorio = tarea.getDocumentoRepositorio();
        return new LineaDocumentoPendienteDto(tarea.getId(), isValidacion, documentoRepositorio.getNombreDocumento(),
                documentoRepositorio.getUriDocumento(), documentoRepositorio.getDescripcionDocumento(), isInCircuito, circuitoDto, isSustituto, isDelegado,
                isUsuarioOriginal);
    }


    private static class ValidacionVisitor implements TareaVisitor {

        private boolean isValidacion;

        @Override
        public void visit(TareaValidacion tareaValidacion) {
            isValidacion = true;
        }

        @Override
        public void visit(TareaFirma tareaFirma) {
            isValidacion = false;
        }

        public boolean isValidacion(Tarea tarea) {
            tarea.accept(this);
            return this.isValidacion;
        }
    }
}
