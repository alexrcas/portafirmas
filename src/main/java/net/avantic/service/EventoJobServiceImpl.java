package net.avantic.service;

import net.avantic.model.dao.EventoJobDao;
import net.avantic.model.entity.*;
import net.avantic.model.type.EstadoEvento;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.time.LocalDateTime;

@ApplicationScoped
public class EventoJobServiceImpl implements EventoJobService {

    @Inject
    EventoJobDao eventoJobDao;

    private final Long NUMERO_MAXIMO_REINTENTOS = 3L;

    @Override
    public void crearEventoJobPersistirFirmaCircuito(CircuitoCompletado circuitoCompletado) {
        EventoJobPersistirFirmaCircuito evento = new EventoJobPersistirFirmaCircuito(LocalDateTime.now(), LocalDateTime.now(),
                EstadoEvento.PENDIENTE, NUMERO_MAXIMO_REINTENTOS, 0L, circuitoCompletado, "");

        eventoJobDao.persist(evento);

        procesarEvento(evento);
    }

    @Override
    public void crearEventoJobActualizarBackoffice(CircuitoCompletado circuitoCompletado) {
        
    }

    @Override
    public void crearEventoJobPersistirFirmaDocumento(DocumentoFirmado documentoFirmado) {
        EventoJobPersistirFirmaDocumento evento = new EventoJobPersistirFirmaDocumento(LocalDateTime.now(), LocalDateTime.now(),
                EstadoEvento.PENDIENTE, NUMERO_MAXIMO_REINTENTOS, 0L, documentoFirmado, "");

        eventoJobDao.persist(evento);

        procesarEvento(evento);
    }

    @Override
    public void crearEventoJobActualizarBackoffice(DocumentoFirmado documentoFirmado) {

    }

    @Override
    public void procesarEvento(EventoJob evento) {

    }
}
