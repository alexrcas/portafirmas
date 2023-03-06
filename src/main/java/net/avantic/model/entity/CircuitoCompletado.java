package net.avantic.model.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class CircuitoCompletado extends PanacheEntityBase {

    private Long id;
    private LocalDateTime fecha;
    GrupoTareaCircuito grupoTareaCircuito;

    protected CircuitoCompletado() {
    }

    public CircuitoCompletado(GrupoTareaCircuito grupoTareaCircuito) {
        this.fecha = LocalDateTime.now();
        this.grupoTareaCircuito = grupoTareaCircuito;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    @ManyToOne
    @JoinColumn(name = "idGrupoTareaCircuito")
    public GrupoTareaCircuito getGrupoTareaCircuito() {
        return grupoTareaCircuito;
    }

    public void setGrupoTareaCircuito(GrupoTareaCircuito grupoTareaCircuito) {
        this.grupoTareaCircuito = grupoTareaCircuito;
    }
}
