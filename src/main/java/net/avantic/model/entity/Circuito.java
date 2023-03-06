package net.avantic.model.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;
import java.util.Optional;

@Entity
public class Circuito extends PanacheEntity {

    private String nombre;

    protected Circuito() {
    }

    public Circuito(String nombre) {
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
