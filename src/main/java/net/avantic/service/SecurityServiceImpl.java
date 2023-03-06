package net.avantic.service;

import io.quarkus.security.identity.SecurityIdentity;
import net.avantic.model.entity.Usuario;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class SecurityServiceImpl implements SecurityService {

    @Inject
    SecurityIdentity identity;

    @Inject
    UsuarioService usuarioService;

    @Override
    public Usuario getUsuarioActivo() {
        String dni = identity.getPrincipal().getName();
        return usuarioService.findUsuarioByDni(dni);
    }
}
