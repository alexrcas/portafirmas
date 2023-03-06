package net.avantic.model.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;

@Entity
public class DocumentoValidado extends PanacheEntityBase {

    private Long id;
    private TareaValidacion tareaValidacion;

    protected DocumentoValidado() {
    }

    public DocumentoValidado(TareaValidacion tareaValidacion) {
        this.tareaValidacion = tareaValidacion;
        //todo arodriguez: solo para pruebas
    }

    private void setId(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    @ManyToOne
    @JoinColumn(name = "idTareaValidacion")
    public Tarea getTareaValidacion() {
        return tareaValidacion;
    }

    public void setTareaValidacion(TareaValidacion tareaValidacion) {
        this.tareaValidacion = tareaValidacion;
    }
}
