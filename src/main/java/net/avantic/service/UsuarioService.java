package net.avantic.service;

import net.avantic.model.entity.Circuito;
import net.avantic.model.entity.Usuario;
import net.avantic.type.DNI;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {

    List<Usuario> listarUsuarios();

    List<Usuario> listarUsuariosByCircuito(Circuito circuito);

    List<Usuario> listarUsuariosNoAusentes();

    Optional<Usuario> findUsuarioById(Long id);

    void eliminarUsuario(Long id);

    void crearUsuario(DNI dni, String nombre);

    void actualizarUsuario(Usuario usuario);

    Usuario findUsuarioByDni(String dni);
}
