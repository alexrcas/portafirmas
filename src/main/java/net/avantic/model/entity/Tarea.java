package net.avantic.model.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import net.avantic.model.type.TareaVisitor;

import javax.persistence.*;
import java.util.Objects;
import java.util.Optional;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Tarea extends PanacheEntityBase {

    private Long id;
    private DocumentoRepositorio documentoRepositorio;
    private Usuario usuario;

    protected Tarea() {
    }

    public Tarea(DocumentoRepositorio documentoRepositorio, Usuario usuario) {
        this.documentoRepositorio = documentoRepositorio;
        this.usuario = usuario;
    }

    abstract public void accept(TareaVisitor visitor);

    public static Optional<Tarea> findByDocumentoRepositorio(DocumentoRepositorio documentoRepositorio) {
        return find("documentoRepositorio", documentoRepositorio).firstResultOptional();
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
    @JoinColumn(name = "idDocumentoRepositorio")
    public DocumentoRepositorio getDocumentoRepositorio() {
        return documentoRepositorio;
    }

    public void setDocumentoRepositorio(DocumentoRepositorio documentoRepositorio) {
        this.documentoRepositorio = documentoRepositorio;
    }

    @ManyToOne
    @JoinColumn(name = "idUsuario")
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tarea that = (Tarea) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
