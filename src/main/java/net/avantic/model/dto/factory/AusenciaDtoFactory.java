package net.avantic.model.dto.factory;

import net.avantic.model.dto.AusenciaDto;
import net.avantic.model.dto.UsuarioDto;
import net.avantic.model.entity.Ausencia;
import net.avantic.model.entity.Usuario;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AusenciaDtoFactory {

    public AusenciaDto newDto(Ausencia ausencia) {
        Usuario usuarioAusente = ausencia.getAusente().getUsuario();
        Usuario usuarioSustituto = ausencia.getSustitucion().getUsuario();

        UsuarioDto ausenteDto = UsuarioDtoFactory.newDto(usuarioAusente);
        UsuarioDto sustitutoDto = UsuarioDtoFactory.newDto(usuarioSustituto);

        return new AusenciaDto(ausenteDto, sustitutoDto);
    }
}
