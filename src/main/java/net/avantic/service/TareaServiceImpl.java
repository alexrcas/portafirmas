package net.avantic.service;

import net.avantic.model.dao.*;
import net.avantic.model.entity.*;
import net.avantic.type.EstadoTarea;
import net.avantic.type.UuidDocumento;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
public class TareaServiceImpl implements TareaService {

    @Inject
    SecurityService securityService;

    @Inject
    TareaDao tareaDao;

    @Inject
    DocumentoRechazadoDao documentoRechazadoDao;

    @Inject
    DocumentoFirmadoDao documentoFirmadoDao;

    @Inject
    TareaOmitidaDao tareaOmitidaDao;

    @Inject
    PertenenciaTareaCircuitoDao pertenenciaTareaCircuitoDao;

    @Inject
    DocumentoRepositorioDao documentoRepositorioDao;

    @Inject
    PertenenciaUsuarioCircuitoDao pertenenciaUsuarioCircuitoDao;

    @Inject
    TareaDelegadaDao tareaDelegadaDao;

    @Inject
    FirmaSustitutoDao firmaSustitutoDao;

    @Inject
    AusenciaDao ausenciaDao;

    @Inject
    SustitucionDao sustitucionDao;

    @Inject
    FirmaDelegadaDao firmaDelegadaDao;

    @Inject
    DocumentoValidadoDao documentoValidadoDao;

    @Inject
    RepositorioService repositorioService;


    @Override
    public List<Tarea> listTareasPendientesUsuario(Usuario usuario) {
        return tareaDao.listPendientes(usuario);
    }


    @Override
    public List<Tarea> listTareasPendientesYOmitidasUsuario(Usuario usuario) {
        return tareaDao.listPendientesYOmitidas(usuario);
    }

    @Override
    public Tarea getTarea(Long id) {
        return tareaDao.get(id);
    }


    @Override
    public List<DocumentoRechazado> listDocumentosRechazados(Usuario usuario) {
        return documentoRechazadoDao.listByUsuario(usuario);
    }


    @Override
    public List<DocumentoFirmado> listDocumentosFirmados(Usuario usuario) {
        return documentoFirmadoDao.listByUsuario(usuario);
    }


    @Override
    public List<Tarea> listTareasMismoGrupo(Tarea tarea) {
        Optional<PertenenciaTareaCircuito> pertenenciaTarea = pertenenciaTareaCircuitoDao.findByTarea(tarea);
        GrupoTareaCircuito grupoTareaCircuito = pertenenciaTarea.get().getGrupoTareaCircuito();
        return tareaDao.listMismoGrupo(grupoTareaCircuito);
    }

    @Override
    public boolean isDocumentoPendienteFirma(UuidDocumento uuidDocumento) {
        return documentoRepositorioDao.findByUuidDocumento(uuidDocumento)
                .map(tareaDao::findPendienteFirmaByDocumento)
                .map(list -> !list.isEmpty())
                .orElse(false);
    }

    @Override
    public boolean isDisponible(Tarea tarea, Usuario usuario) {
        Optional<PertenenciaTareaCircuito> pertenenciaTareaCircuitoOpt = pertenenciaTareaCircuitoDao.findByTarea(tarea);

        if (pertenenciaTareaCircuitoOpt.isEmpty()) {
            return true;
        }

        // Se obtienen los usuarios del circuito al que pertenece la tarea
        Circuito circuito = pertenenciaTareaCircuitoOpt.get().getCircuito();
        List<PertenenciaUsuarioCircuito> pertenenciasUsuariosCircuitos = pertenenciaUsuarioCircuitoDao.listByCircuito(circuito);

        //Se obtiene la posición del usuario en el circuito
        Long posicionUsuario = pertenenciasUsuariosCircuitos.stream()
                .filter(pertenenciaUsuarioCircuito -> pertenenciaUsuarioCircuito.getUsuario().getDni().equals(usuario.getDni()))
                .findFirst()
                .map(PertenenciaUsuarioCircuito::getPosicion)
                .orElseThrow(RuntimeException::new);


        //Se obtienen todos los usuarios del circuito con una posición menor que la del usuario
        List<Usuario> usuariosPrioridad = pertenenciasUsuariosCircuitos.stream()
                .filter(p -> p.getPosicion() < posicionUsuario)
                .map(PertenenciaUsuarioCircuito::getUsuario)
                .collect(Collectors.toList());

        //Se obtienen las tareas del mismo grupo y se contemplan solo las que pertenecen a usuarios con más prioridad.
        //Si todas estas tareas no están en estado pendiente, entonces han sido despachadas u omitidas y es el turno del usuario.
        return listTareasMismoGrupo(tarea).stream()
                .filter(t -> usuariosPrioridad.contains(t.getUsuario()))
                .map(this::getEstadoTarea)
                .noneMatch(e -> e.equals(EstadoTarea.PENDIENTE));
    }

    @Override
    public boolean isDelegada(Tarea tarea) {
        return tareaDelegadaDao.findByTarea(tarea)
                .isPresent();
    }


    @Override
    public EstadoTarea getEstadoTarea(Tarea tarea) {

        Optional<DocumentoRechazado> documentoRechazado = documentoRechazadoDao.findByTarea(tarea);
        if (documentoRechazado.isPresent()) {
            return EstadoTarea.RECHAZADO;
        }

        Optional<DocumentoFirmado> documentoFirmado = documentoFirmadoDao.findByTarea(tarea);
        if (documentoFirmado.isPresent()) {
            return EstadoTarea.FIRMADO;
        }

        Optional<DocumentoValidado> documentoValidado = documentoValidadoDao.findByTarea(tarea);
        if (documentoValidado.isPresent()) {
            return EstadoTarea.VALIDADO;
        }


        Optional<TareaOmitida> tareaOmitida = tareaOmitidaDao.findByTarea(tarea);
        if (tareaOmitida.isPresent()) {
            return EstadoTarea.OMITIDO;
        }

        return EstadoTarea.PENDIENTE;
    }

    @Override
    public List<Tarea> listTareasPendientesSustituto(Usuario usuario) {
        List<Sustitucion> sustitutos = sustitucionDao.listByUsuario(usuario);

        if (sustitutos.isEmpty()) {
            return List.of();
        }

        List<Ausencia> ausencias = sustitutos.stream()
                .map(st -> ausenciaDao.listBySustitucion(st))
                .flatMap(List::stream)
                .collect(Collectors.toList());

        List<Ausente> ausentes = ausencias.stream()
                .map(Ausencia::getAusente)
                .collect(Collectors.toList());

        return ausentes.stream()
                .map(Ausente::getUsuario)
                .map(usuarioAusente ->
                    listTareasPendientesUsuario(usuarioAusente).stream()
                            .filter(t -> isDisponible(t, usuarioAusente))
                            .collect(Collectors.toList())
                )
                .flatMap(List::stream)
                .collect(Collectors.toList());

    }


    @Override
    public List<Tarea> listTareasDelegadasPendientes(Usuario usuario) {
        return tareaDelegadaDao.listPendientesByUsuario(usuario).stream()
                .map(TareaDelegada::getTarea)
                .collect(Collectors.toList());
    }


    @Override
    public List<DocumentoFirmado> listDocumentosFirmadosComoSustituto(Usuario usuario) {
        return firmaSustitutoDao.listByUsuario(usuario).stream()
                .map(FirmaSustituto::getDocumentoFirmado)
                .collect(Collectors.toList());
    }

    @Override
    public void omitirTarea(Tarea tarea) {
        TareaOmitida tareaOmitida = new TareaOmitida(tarea);
        tareaOmitidaDao.persist(tareaOmitida);
    }

    @Override
    public void delegarTarea(Tarea tarea, Usuario usuario) {
        TareaDelegada tareaDelegada = new TareaDelegada(tarea, usuario);
        tareaDelegadaDao.persist(tareaDelegada);
    }

    @Override
    public void validarTarea(TareaValidacion tareaValidacion) {
        DocumentoValidado documentoValidado = new DocumentoValidado(tareaValidacion);
        documentoValidadoDao.persist(documentoValidado);
        comprobarProcesoFirmaFinalizado(tareaValidacion);
    }

    @Override
    public void firmarTarea(TareaFirma tarea, String firma) {
        DocumentoFirmado documentoFirmado = new DocumentoFirmado(tarea, firma);
        documentoFirmadoDao.persist(documentoFirmado);
        comprobarProcesoFirmaFinalizado(tarea);
    }

    @Override
    public void firmarTareaSustituto(TareaFirma tarea, String firma) {
        Usuario sustituto = securityService.getUsuarioActivo();
        DocumentoFirmado documentoFirmado = new DocumentoFirmado(tarea, firma);
        documentoFirmadoDao.persist(documentoFirmado);
        FirmaSustituto firmaSustituto = new FirmaSustituto(documentoFirmado, sustituto);
        firmaSustitutoDao.persist(firmaSustituto);

        comprobarProcesoFirmaFinalizado(tarea);
    }

    @Override
    public void firmarTareaDelegado(TareaFirma tarea, String firma) {
        Usuario delegado = securityService.getUsuarioActivo();
        DocumentoFirmado documentoFirmado = new DocumentoFirmado(tarea, firma);
        documentoFirmadoDao.persist(documentoFirmado);

        FirmaDelegada firmaDelegada = new FirmaDelegada(documentoFirmado, delegado);
        firmaDelegadaDao.persist(firmaDelegada);

        comprobarProcesoFirmaFinalizado(tarea);
    }


    private void comprobarProcesoFirmaFinalizado(Tarea tarea) {
        Optional<PertenenciaTareaCircuito> pertenenciaTareaCircuito = pertenenciaTareaCircuitoDao.findByTarea(tarea);

        if (pertenenciaTareaCircuito.isEmpty()) {
            //crear evento firma completada
            return;
        }

        GrupoTareaCircuito grupoTareaCircuito = pertenenciaTareaCircuito.get().getGrupoTareaCircuito();
        comprobarCircuitoFinalizado(tarea, grupoTareaCircuito);
    }


    private void comprobarCircuitoFinalizado(Tarea tarea, GrupoTareaCircuito grupoTareaCircuito) {
        boolean isCircuitoCompletado = listTareasMismoGrupo(tarea).stream()
                .map(this::getEstadoTarea)
                .allMatch(e -> e.equals(EstadoTarea.FIRMADO) || e.equals(EstadoTarea.OMITIDO));

        if (!isCircuitoCompletado) {
            return;
        }

        new CircuitoCompletado(grupoTareaCircuito).persist();

        List<DocumentoFirmado> documentosFirmados = listTareasMismoGrupo(tarea).stream()
                .map(documentoFirmadoDao::findByTarea)
                .flatMap(Optional::stream)
                .collect(Collectors.toList());

        //todo arodriguez: escribir implementación del webservice
        //repositorioService.enviarFirmas(documentosFirmados);
    }

    @Override
    public void rechazarTareaSustituto(Tarea tarea, String motivo) {
        Usuario sustituto = securityService.getUsuarioActivo();
        Usuario usuario = tarea.getUsuario();
        listTareasMismoGrupo(tarea).stream()
                    .forEach(t -> {
                        DocumentoRechazado d = new DocumentoRechazado(t, usuario, motivo);
                        documentoRechazadoDao.persist(d);
                        RechazoSustituto r = new RechazoSustituto(d, sustituto);
                        r.persist();
                    });
    }

    @Override
    public void rechazarTareaDelegado(Tarea tarea, String motivo) {
        Usuario delegado = securityService.getUsuarioActivo();
        Usuario usuario = tarea.getUsuario();
        listTareasMismoGrupo(tarea).stream()
                .forEach(t -> {
                    DocumentoRechazado d = new DocumentoRechazado(t, usuario, motivo);
                    documentoRechazadoDao.persist(d);
                    RechazoDelegado r = new RechazoDelegado(d, delegado);
                    r.persist();
                });
    }

    @Override
    public void rechazarTarea(Tarea tarea, String motivo) {
        listTareasMismoGrupo(tarea).stream()
                    .forEach(t -> {
                        DocumentoRechazado d = new DocumentoRechazado(t, t.getUsuario(), motivo);
                        documentoRechazadoDao.persist(d);
                    });
    }

    @Override
    public List<Tarea> listTareasFirmasEnCircuitoSustituto(Usuario usuario) {
        List<Sustitucion> sustituciones = sustitucionDao.listByUsuario(usuario);

        if (sustituciones.isEmpty()) {
            return List.of();
        }

        List<Ausente> ausentes = sustituciones.stream()
                .map(st -> ausenciaDao.listBySustitucion(st))
                .flatMap(List::stream)
                .map(Ausencia::getAusente)
                .collect(Collectors.toList());

        return ausentes.stream()
                .map(Ausente::getUsuario)
                .map(usuarioAusente ->
                        listTareasPendientesUsuario(usuarioAusente).stream()
                                .filter(t -> !isDisponible(t, usuarioAusente))
                                .collect(Collectors.toList())
                )
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

}
