package net.avantic.model.dto.factory;

import net.avantic.model.dao.AusenteDao;
import net.avantic.model.dao.TareaDelegadaDao;
import net.avantic.model.dto.UsuarioDto;
import net.avantic.model.dto.InformacionCircuitoDto;
import net.avantic.model.dto.InformacionTareaDto;
import net.avantic.model.entity.*;
import net.avantic.service.TareaService;
import net.avantic.type.EstadoTarea;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@ApplicationScoped
public class InformacionCircuitoDtoFactory {

    @Inject
    TareaService tareaService;

    @Inject
    TareaDelegadaDao tareaDelegadaDao;

    public InformacionCircuitoDto newDto(List<Tarea> tareas) {

        List<InformacionTareaDto> informacionTareas = tareas.stream()
                .map(tarea -> {
                    EstadoTarea estadoTarea = tareaService.getEstadoTarea(tarea);
                    UsuarioDto usuarioDto = UsuarioDtoFactory.newDto(tarea.getUsuario());

                    boolean isAusente = isUsuarioAusente(tarea);
                    UsuarioDto ausente = findAusente(tarea)
                            .map(UsuarioDtoFactory::newDto)
                            .orElseGet(() -> null);

                    UsuarioDto firmadoSustituto = findFirmadoSustituto(tarea)
                            .map(UsuarioDtoFactory::newDto)
                            .orElseGet(() -> null);

                    boolean isFirmaSustituto = firmadoSustituto != null;

                    UsuarioDto delegado = findUsuarioDelegado(tarea)
                            .map(UsuarioDtoFactory::newDto)
                            .orElseGet(() -> null);

                    boolean isDelegado = delegado != null;

                    return new InformacionTareaDto(usuarioDto, estadoTarea, isAusente, ausente, isFirmaSustituto, firmadoSustituto, isDelegado, delegado);
                })
                .collect(Collectors.toList());

        return new InformacionCircuitoDto(informacionTareas);
    }

    private Optional<Usuario> findFirmadoSustituto(Tarea tarea) {
        if (!tareaService.getEstadoTarea(tarea).equals(EstadoTarea.FIRMADO)) {
            return Optional.empty();
        }

        DocumentoFirmado documentoFirmado = DocumentoFirmado.find("idTareaFirma", tarea)
                .firstResult();

        return FirmaSustituto.find("idDocumentoFirmado", documentoFirmado)
                .firstResultOptional()
                .map(pe -> (FirmaSustituto) pe)
                .map(FirmaSustituto::getUsuario);
    }

    private boolean isUsuarioAusente(Tarea tarea) {
        return Ausente.find("idUsuario", tarea.getUsuario())
                .firstResultOptional()
                .isPresent();
    }


    private Optional<Usuario> findAusente(Tarea tarea) {
        Optional<Ausente> ausenteOpt = Ausente.find("idUsuario", tarea.getUsuario())
                .firstResultOptional();

        if (ausenteOpt.isEmpty()) {
            return Optional.empty();
        }

        Optional<Ausencia> ausenciaOpt = Ausencia.find("idAusente", ausenteOpt.get()).firstResultOptional();
        if (ausenciaOpt.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(ausenciaOpt.get().getSustitucion().getUsuario());
    }


    private Optional<Usuario> findUsuarioDelegado(Tarea tarea) {
        return tareaDelegadaDao.findByTarea(tarea)
                .map(TareaDelegada::getUsuario);
    }
}
