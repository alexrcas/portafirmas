package net.avantic.model.dao;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import net.avantic.model.entity.DocumentoRepositorio;
import net.avantic.model.entity.Usuario;
import net.avantic.model.entity.GrupoTareaCircuito;
import net.avantic.model.entity.Tarea;

import java.util.List;

public interface TareaDao extends PanacheRepository<Tarea> {

    Tarea get(Long id);

    void save(Tarea tarea);

    List<Tarea> listPendientes(Usuario usuario);

    List<Tarea> listPendientesYOmitidas(Usuario usuario);

    List<Tarea> listMismoGrupo(GrupoTareaCircuito grupoTareaCircuito);

    List<Tarea> findPendienteFirmaByDocumento(DocumentoRepositorio documentoRepositorio);
}
