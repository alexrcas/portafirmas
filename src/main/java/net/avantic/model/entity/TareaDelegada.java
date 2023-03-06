package net.avantic.model.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;

@Entity
public class TareaDelegada extends PanacheEntityBase {

    private Long id;
    private Tarea tarea;
    private Usuario usuario;

    protected TareaDelegada() {
    }

    private void setId(Long id) {
        this.id = id;
    }

    public TareaDelegada(Tarea tarea, Usuario usuario) {
        this.tarea = tarea;
        this.usuario = usuario;
    }



    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
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
    @JoinColumn(name = "idUsuario")
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
