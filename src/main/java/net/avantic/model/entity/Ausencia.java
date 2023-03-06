package net.avantic.model.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;

@Entity
public class Ausencia extends PanacheEntityBase {

    private Long id;
    private Ausente ausente;
    private Sustitucion sustitucion;

    protected Ausencia() {
    }

    public Ausencia(Ausente ausente, Sustitucion sustitucion) {
        this.ausente = ausente;
        this.sustitucion = sustitucion;
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
    @JoinColumn(name = "idAusente")
    public Ausente getAusente() {
        return ausente;
    }

    public void setAusente(Ausente ausente) {
        this.ausente = ausente;
    }

    @ManyToOne
    @JoinColumn(name = "idSustitucion")
    public Sustitucion getSustitucion() {
        return sustitucion;
    }

    public void setSustitucion(Sustitucion sustitucion) {
        this.sustitucion = sustitucion;
    }
}
