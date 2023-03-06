package net.avantic.model.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;

@Entity
public class FirmaDelegada extends PanacheEntityBase {

    private Long id;
    private DocumentoFirmado documentoFirmado;
    private Usuario usuario;

    protected FirmaDelegada() {
    }

    public FirmaDelegada(DocumentoFirmado documentoFirmado, Usuario usuario) {
        this.documentoFirmado = documentoFirmado;
        this.usuario = usuario;
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
    @JoinColumn(name = "idDocumentoFirmado")
    public DocumentoFirmado getDocumentoFirmado() {
        return documentoFirmado;
    }

    public void setDocumentoFirmado(DocumentoFirmado documentoFirmado) {
        this.documentoFirmado = documentoFirmado;
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
