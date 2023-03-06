package net.avantic.model.entity;

import net.avantic.model.type.TipoPertenenciaVisitor;

import javax.persistence.Entity;

@Entity
public class PertenenciaTipoFirmante extends PertenenciaUsuarioCircuito {

    @Override
    public void accept(TipoPertenenciaVisitor visitor) {
        visitor.visit(this);
    }

    protected PertenenciaTipoFirmante() {
    }

    public PertenenciaTipoFirmante(Usuario usuario, Circuito circuito, Long posicion) {
        super(usuario, circuito, posicion);
    }
}
