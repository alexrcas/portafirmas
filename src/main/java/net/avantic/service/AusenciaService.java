package net.avantic.service;

import net.avantic.model.entity.Ausencia;
import net.avantic.model.entity.Usuario;

import java.util.List;

public interface AusenciaService {

    List<Ausencia> list();

    void crearAusencia(Usuario usuarioAusente, Usuario usuarioSustituto);
}
