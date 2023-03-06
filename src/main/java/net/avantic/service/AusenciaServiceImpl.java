package net.avantic.service;

import net.avantic.model.dao.AusenciaDao;
import net.avantic.model.dao.AusenteDao;
import net.avantic.model.dao.SustitucionDao;
import net.avantic.model.entity.Ausencia;
import net.avantic.model.entity.Ausente;
import net.avantic.model.entity.Usuario;
import net.avantic.model.entity.Sustitucion;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class AusenciaServiceImpl implements AusenciaService {

    @Inject
    AusenteDao ausenteDao;

    @Inject
    SustitucionDao sustitucionDao;

    @Inject
    AusenciaDao ausenciaDao;

    @Override
    public List<Ausencia> list() {
        return Ausencia.listAll();
    }

    @Override
    public void crearAusencia(Usuario usuarioAusente, Usuario usuarioSustituto) {
        Ausente ausente = new Ausente(usuarioAusente);
        ausenteDao.persist(ausente);

        Sustitucion sustitucion = new Sustitucion(usuarioSustituto);
        sustitucionDao.persist(sustitucion);

        Ausencia ausencia = new Ausencia(ausente, sustitucion);
        ausenciaDao.persist(ausencia);
    }
}
