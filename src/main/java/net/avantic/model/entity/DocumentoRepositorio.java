package net.avantic.model.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import net.avantic.type.UuidDocumento;

import javax.persistence.Entity;
import java.util.Optional;

@Entity
public class DocumentoRepositorio extends PanacheEntity {

    private String uriDocumento;
    private String nombreDocumento;
    private String descripcionDocumento;

    protected DocumentoRepositorio() {
    }

    public DocumentoRepositorio(String uriDocumento, String nombreDocumento, String descripcionDocumento) {
        this.uriDocumento = uriDocumento;
        this.nombreDocumento = nombreDocumento;
        this.descripcionDocumento = descripcionDocumento;
    }

    public String getUriDocumento() {
        return uriDocumento;
    }

    public void setUriDocumento(String uriDocumento) {
        this.uriDocumento = uriDocumento;
    }

    public String getNombreDocumento() {
        return nombreDocumento;
    }

    public void setNombreDocumento(String nombreDocumento) {
        this.nombreDocumento = nombreDocumento;
    }

    public String getDescripcionDocumento() {
        return descripcionDocumento;
    }

    public void setDescripcionDocumento(String descripcionDocumento) {
        this.descripcionDocumento = descripcionDocumento;
    }
}
