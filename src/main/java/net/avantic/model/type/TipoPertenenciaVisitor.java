package net.avantic.model.type;

import net.avantic.model.entity.*;

public interface TipoPertenenciaVisitor {

    void visit(PertenenciaTipoValidador pertenenciaTipoValidador);

    void visit(PertenenciaTipoFirmante pertenenciaTipoFirmante);
}
