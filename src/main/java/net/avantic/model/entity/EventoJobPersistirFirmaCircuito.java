package net.avantic.model.entity;


import net.avantic.model.type.EstadoEvento;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
public class EventoJobPersistirFirmaCircuito extends EventoJob {

    private CircuitoCompletado circuitoCompletado;

    protected EventoJobPersistirFirmaCircuito() { }

    public EventoJobPersistirFirmaCircuito(LocalDateTime fechaCreacion,
                                           LocalDateTime fechaProximoReintento,
                                           EstadoEvento estadoEvento,
                                           Long numeroMaximoIntentos,
                                           Long intentos,
                                           CircuitoCompletado circuitoCompletado,
                                           String respuestaServidor) {
        super(fechaCreacion, fechaProximoReintento, estadoEvento, numeroMaximoIntentos, intentos, respuestaServidor);
        this.circuitoCompletado = circuitoCompletado;
    }

    @ManyToOne
    @JoinColumn(name = "idCircuitoCompletado")
    public CircuitoCompletado getCircuitoCompletado() {
        return circuitoCompletado;
    }

    public void setCircuitoCompletado(CircuitoCompletado circuitoCompletado) {
        this.circuitoCompletado = circuitoCompletado;
    }
}
