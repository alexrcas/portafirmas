package net.avantic.model.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;

@Entity
public class RechazoDelegado extends PanacheEntityBase {

    private Long id;
    private DocumentoRechazado documentoRechazado;
    private Usuario usuario;

    protected RechazoDelegado() {
    }

    public RechazoDelegado(DocumentoRechazado documentoRechazado, Usuario usuario) {
        this.documentoRechazado = documentoRechazado;
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
    @JoinColumn(name = "idDocumentoRechazado")
    public DocumentoRechazado getDocumentoRechazado() {
        return documentoRechazado;
    }

    public void setDocumentoRechazado(DocumentoRechazado documentoRechazado) {
        this.documentoRechazado = documentoRechazado;
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
