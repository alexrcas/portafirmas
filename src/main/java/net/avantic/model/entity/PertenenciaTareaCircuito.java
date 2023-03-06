package net.avantic.model.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;

@Entity
public class PertenenciaTareaCircuito extends PanacheEntityBase {

    private Long id;
    private Tarea tarea;
    private Circuito circuito;
    private GrupoTareaCircuito grupoTareaCircuito;

    protected PertenenciaTareaCircuito() {
    }

    public PertenenciaTareaCircuito(Tarea tarea, Circuito circuito, GrupoTareaCircuito grupoTareaCircuito) {
        this.tarea = tarea;
        this.circuito = circuito;
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

    @ManyToOne
    @JoinColumn(name = "idTarea")
    public Tarea getTarea() {
        return tarea;
    }

    public void setTarea(Tarea tarea) {
        this.tarea = tarea;
    }

    @ManyToOne
    @JoinColumn(name = "idCircuito")
    public Circuito getCircuito() {
        return circuito;
    }

    public void setCircuito(Circuito circuito) {
        this.circuito = circuito;
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
