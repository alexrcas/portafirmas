package net.avantic.model.dto;

import java.io.Serializable;

public class DocumentoDto implements Serializable {

    private String nombre;
    private String descripcion;
    private String uuid;

    public DocumentoDto(String nombre, String descripcion, String uuid) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.uuid = uuid;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
