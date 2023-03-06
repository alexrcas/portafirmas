package net.avantic.service;

import net.avantic.model.entity.CircuitoCompletado;
import net.avantic.model.entity.DocumentoFirmado;
import net.avantic.model.entity.EventoJob;

public interface EventoJobService {

    void crearEventoJobPersistirFirmaCircuito(CircuitoCompletado circuitoCompletado);

    void crearEventoJobActualizarBackoffice(CircuitoCompletado circuitoCompletado);

    void crearEventoJobPersistirFirmaDocumento(DocumentoFirmado documentoFirmado);

    void crearEventoJobActualizarBackoffice(DocumentoFirmado documentoFirmado);

    void procesarEvento(EventoJob evento);
}
