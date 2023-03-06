package net.avantic.model.entity;

import net.avantic.model.type.TareaVisitor;

import javax.persistence.Entity;

@Entity
public class TareaFirma extends Tarea {

    protected TareaFirma() {
    }

    public TareaFirma(DocumentoRepositorio documentoRepositorio, Usuario usuario) {
        super(documentoRepositorio, usuario);
    }

    @Override
    public void accept(TareaVisitor visitor) {
        visitor.visit(this);
    }
}
