package net.avantic.model.type;

import net.avantic.model.entity.TareaFirma;
import net.avantic.model.entity.TareaValidacion;

public interface TareaVisitor {
    
    void visit(TareaValidacion tareaValidacion);

    void visit(TareaFirma tareaFirma);
}
