package net.avantic.service;

import net.avantic.model.entity.Circuito;
import net.avantic.model.entity.Usuario;
import net.avantic.model.entity.DocumentoRepositorio;
import net.avantic.model.entity.Tarea;

import java.util.List;
import java.util.Optional;

public interface CircuitoService {

    List<Circuito> listarCircuitos();

    void crearCircuito(String nombreCircuito, List<Usuario> usuarios);

    Optional<Circuito> findCircuitoById(Long id);

    Optional<Circuito> findByTarea(Tarea tarea);

    void enviarDocumento(Circuito circuito, DocumentoRepositorio documentoRepositorio);
}
