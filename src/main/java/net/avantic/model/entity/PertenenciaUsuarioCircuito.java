package net.avantic.model.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import net.avantic.model.type.TipoPertenenciaVisitor;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class PertenenciaUsuarioCircuito extends PanacheEntityBase {

    private Long id;
    private Usuario usuario;
    private Circuito circuito;
    private Long posicion;

    @Transient
    public abstract void accept(TipoPertenenciaVisitor visitor);

    protected PertenenciaUsuarioCircuito() {
    }

    public PertenenciaUsuarioCircuito(Usuario usuario, Circuito circuito, Long posicion) {
        this.usuario = usuario;
        this.circuito = circuito;
        this.posicion = posicion;
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
    @JoinColumn(name = "idUsuario")
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @ManyToOne
    @JoinColumn(name = "idCircuito")
    public Circuito getCircuito() {
        return circuito;
    }

    public void setCircuito(Circuito circuito) {
        this.circuito = circuito;
    }

    public Long getPosicion() {
        return posicion;
    }

    public void setPosicion(Long posicion) {
        this.posicion = posicion;
    }
}
