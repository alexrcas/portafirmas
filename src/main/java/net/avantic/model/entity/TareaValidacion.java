package net.avantic.model.entity;

import net.avantic.model.type.TareaVisitor;

import javax.persistence.Entity;

@Entity
public class TareaValidacion extends Tarea {

    protected TareaValidacion() {
    }

    public TareaValidacion(DocumentoRepositorio documentoRepositorio, Usuario usuario) {
        super(documentoRepositorio, usuario);
    }

    @Override
    public void accept(TareaVisitor visitor) {
        visitor.visit(this);
    }
}
