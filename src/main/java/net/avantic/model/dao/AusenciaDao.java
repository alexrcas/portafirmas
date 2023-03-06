package net.avantic.model.dao;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import net.avantic.model.entity.Ausencia;
import net.avantic.model.entity.Sustitucion;

import java.util.List;

public interface AusenciaDao extends PanacheRepository<Ausencia> {

    List<Ausencia> listBySustitucion(Sustitucion st);
}
