package net.avantic.model.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;

@Entity
public class DocumentoRechazado extends PanacheEntityBase {

    private Long id;
    private Tarea tarea;
    private Usuario autorRechazo;
    private String motivo;

    protected DocumentoRechazado() {
    }

    public DocumentoRechazado(Tarea tarea, Usuario autorRechazo, String motivo) {
        this.tarea = tarea;
        this.autorRechazo = autorRechazo;
        this.motivo = motivo;
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
    @JoinColumn(name = "idTarea")
    public Tarea getTarea() {
        return tarea;
    }

    public void setTarea(Tarea tarea) {
        this.tarea = tarea;
    }


    @ManyToOne
    @JoinColumn(name = "idUsuario")
    public Usuario getAutorRechazo() {
        return autorRechazo;
    }

    public void setAutorRechazo(Usuario autorRechazo) {
        this.autorRechazo = autorRechazo;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }
}
