package net.avantic.model.dao;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import net.avantic.model.entity.FirmaSustituto;
import net.avantic.model.entity.Usuario;

import java.util.List;

public interface FirmaSustitutoDao extends PanacheRepository<FirmaSustituto> {

    List<FirmaSustituto> listByUsuario(Usuario usuario);
}
