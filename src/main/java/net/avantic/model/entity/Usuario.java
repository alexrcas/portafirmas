package net.avantic.model.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;
import java.util.Optional;

@Entity
public class Usuario extends PanacheEntity {

    private String dni;
    private String nombre;

    protected Usuario() {
    }

    public Usuario(String dni, String nombre) {
        this.dni = dni;
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public static Optional<Usuario> findByDni(String dni) {
        return find("dni", dni).firstResultOptional();
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
