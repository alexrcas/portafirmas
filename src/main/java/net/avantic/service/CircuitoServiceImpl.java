package net.avantic.service;

import net.avantic.model.dao.*;
import net.avantic.model.entity.*;
import net.avantic.model.type.TipoPertenenciaVisitor;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.Optional;
import java.util.stream.LongStream;

@ApplicationScoped
public class CircuitoServiceImpl implements CircuitoService {

    @Inject
    UsuarioService usuarioService;

    @Inject
    CircuitoDao circuitoDao;

    @Inject
    TareaDao tareaDao;

    @Inject
    PertenenciaTareaCircuitoDao pertenenciaTareaCircuitoDao;

    @Inject
    GrupoTareaCircuitoDao grupoTareaCircuitoDao;

    @Inject
    PertenenciaUsuarioCircuitoDao pertenenciaUsuarioCircuitoDao;

    @Override
    public List<Circuito> listarCircuitos() {
        return Circuito.listAll();
    }

    @Override
    public void crearCircuito(String nombreCircuito, List<Usuario> usuarios) {
        Circuito circuito = new Circuito(nombreCircuito);
        circuitoDao.persist(circuito);

        LongStream.range(0, usuarios.size())
                .mapToObj(pos -> new PertenenciaTipoFirmante(usuarios.get((int)pos), circuito, pos))
                .forEach(pertenenciaUsuarioCircuitoDao::persist);
    }

    @Override
    public Optional<Circuito> findCircuitoById(Long id) {
        return circuitoDao.findCircuitoById(id);
    }

    @Override
    public Optional<Circuito> findByTarea(Tarea tarea) {
        return circuitoDao.findByTarea(tarea);
    }

    @Override
    public void enviarDocumento(Circuito circuito, DocumentoRepositorio documentoRepositorio) {
        GrupoTareaCircuito grupoTareaCircuito = new GrupoTareaCircuito();
        grupoTareaCircuitoDao.persist(grupoTareaCircuito);

        pertenenciaUsuarioCircuitoDao.listByCircuito(circuito).stream()
                .forEach(p -> {
                    Tarea tarea = crearTarea(documentoRepositorio, p);
                    tareaDao.persist(tarea);

                    PertenenciaTareaCircuito pertenenciaTareaCircuito = new PertenenciaTareaCircuito(tarea, circuito, grupoTareaCircuito);
                    pertenenciaTareaCircuitoDao.persist(pertenenciaTareaCircuito);
                });

    }


    private Tarea crearTarea(DocumentoRepositorio documentoRepositorio, PertenenciaUsuarioCircuito pertenenciaUsuarioCircuito) {
        CrearTipoPertenenciaVisitorImpl visitor = new CrearTipoPertenenciaVisitorImpl(documentoRepositorio, pertenenciaUsuarioCircuito);
        return visitor.crearTarea();
    }

    static class CrearTipoPertenenciaVisitorImpl implements TipoPertenenciaVisitor {

        private final DocumentoRepositorio documentoRepositorio;
        private Tarea tarea = null;

        public CrearTipoPertenenciaVisitorImpl(DocumentoRepositorio documentoRepositorio, PertenenciaUsuarioCircuito pertenenciaUsuarioCircuito) {
            this.documentoRepositorio = documentoRepositorio;
            pertenenciaUsuarioCircuito.accept(this);
        }

        @Override
        public void visit(PertenenciaTipoValidador pertenenciaTipoValidador) {
            this.tarea = new TareaValidacion(documentoRepositorio, pertenenciaTipoValidador.getUsuario());
        }

        @Override
        public void visit(PertenenciaTipoFirmante pertenenciaTipoFirmante) {
            this.tarea = new TareaFirma(documentoRepositorio, pertenenciaTipoFirmante.getUsuario());
        }

        public Tarea crearTarea() {
            return this.tarea;
        }
    }

}
