package net.avantic.service;

import net.avantic.model.dao.UsuarioDao;
import net.avantic.model.entity.Circuito;
import net.avantic.model.entity.Usuario;
import net.avantic.type.DNI;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class UsuarioServiceImpl implements UsuarioService {

    @Inject
    UsuarioDao usuarioDao;

    @Override
    public List<Usuario> listarUsuarios() {
        return usuarioDao.listAll();
    }

    @Override
    public List<Usuario> listarUsuariosByCircuito(Circuito circuito) {
        return usuarioDao.listByCircuito(circuito);

    }

    @Override
    public List<Usuario> listarUsuariosNoAusentes() {
        return usuarioDao.listNoAusentes();
    }

    @Override
    public Optional<Usuario> findUsuarioById(Long id) {
        return usuarioDao.findByIdOptional(id);
    }

    @Override
    public void eliminarUsuario(Long id) {
        usuarioDao.deleteById(id);
    }

    @Override
    public void crearUsuario(DNI dni, String nombre) {
        Usuario usuario = new Usuario(dni.getDni(), nombre);
        usuarioDao.persist(usuario);
    }

    @Override
    public void actualizarUsuario(Usuario usuario) {
        usuarioDao.persist(usuario);
    }

    @Override
    public Usuario findUsuarioByDni(String dni) {
        return usuarioDao.findByDni(dni);
    }
}
