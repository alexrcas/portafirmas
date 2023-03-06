package net.avantic.service;

import net.avantic.model.entity.*;
import net.avantic.type.EstadoTarea;
import net.avantic.type.UuidDocumento;

import java.util.List;

public interface TareaService {

    List<Tarea> listTareasPendientesUsuario(Usuario usuario);

    List<Tarea> listTareasPendientesYOmitidasUsuario(Usuario usuario);

    Tarea getTarea(Long id);

    List<DocumentoRechazado> listDocumentosRechazados(Usuario usuario);

    List<DocumentoFirmado> listDocumentosFirmados(Usuario usuario);

    List<Tarea> listTareasMismoGrupo(Tarea tarea);

    boolean isDocumentoPendienteFirma(UuidDocumento uuidDocumento);

    boolean isDisponible(Tarea tarea, Usuario usuario);

    boolean isDelegada(Tarea tarea);

    EstadoTarea getEstadoTarea(Tarea tarea);

    List<Tarea> listTareasPendientesSustituto(Usuario usuario);

    List<Tarea> listTareasDelegadasPendientes(Usuario usuario);

    void firmarTarea(TareaFirma tarea, String firma);

    void firmarTareaSustituto(TareaFirma tarea, String firma);

    void firmarTareaDelegado(TareaFirma tarea, String firma);

    void rechazarTareaSustituto(Tarea tarea, String motivo);

    void rechazarTareaDelegado(Tarea tarea, String motivo);

    void rechazarTarea(Tarea tarea, String motivo);

    List<Tarea> listTareasFirmasEnCircuitoSustituto(Usuario usuario);

    List<DocumentoFirmado> listDocumentosFirmadosComoSustituto(Usuario usuario);

    void omitirTarea(Tarea tarea);

    void delegarTarea(Tarea tarea, Usuario usuario);

    void validarTarea(TareaValidacion tareaValidacion);
}
