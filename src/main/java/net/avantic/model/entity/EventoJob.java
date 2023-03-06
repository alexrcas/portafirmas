package net.avantic.model.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import net.avantic.model.type.EstadoEvento;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class EventoJob extends PanacheEntityBase {

    protected Long id;
    protected LocalDateTime fechaCreacion;
    protected LocalDateTime fechaProximoReintento;
    protected EstadoEvento estadoEvento;
    protected Long numeroMaximoIntentos;
    protected Long intentos;
    protected String respuestaServidor;

    protected EventoJob() {
    }

    public EventoJob(LocalDateTime fechaCreacion,
                     LocalDateTime fechaProximoReintento,
                     EstadoEvento estadoEvento,
                     Long numeroMaximoIntentos,
                     Long intentos,
                     String respuestaServidor) {
        this.fechaCreacion = fechaCreacion;
        this.fechaProximoReintento = fechaProximoReintento;
        this.estadoEvento = estadoEvento;
        this.numeroMaximoIntentos = numeroMaximoIntentos;
        this.intentos = intentos;
        this.respuestaServidor = respuestaServidor;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDateTime getFechaProximoReintento() {
        return fechaProximoReintento;
    }

    public void setFechaProximoReintento(LocalDateTime fechaProximoReintento) {
        this.fechaProximoReintento = fechaProximoReintento;
    }

    public EstadoEvento getEstadoEvento() {
        return estadoEvento;
    }

    public void setEstadoEvento(EstadoEvento estadoEvento) {
        this.estadoEvento = estadoEvento;
    }

    public Long getNumeroMaximoIntentos() {
        return numeroMaximoIntentos;
    }

    public void setNumeroMaximoIntentos(Long numeroMaximoIntentos) {
        this.numeroMaximoIntentos = numeroMaximoIntentos;
    }

    public Long getIntentos() {
        return intentos;
    }

    public void setIntentos(Long intentos) {
        this.intentos = intentos;
    }

    public String getRespuestaServidor() {
        return respuestaServidor;
    }

    public void setRespuestaServidor(String respuestaServidor) {
        this.respuestaServidor = respuestaServidor;
    }
}
