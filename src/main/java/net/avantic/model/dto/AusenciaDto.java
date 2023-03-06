package net.avantic.model.dto;

public class AusenciaDto {

    private UsuarioDto usuarioAusente;
    private UsuarioDto usuarioSustituto;

    public AusenciaDto(UsuarioDto usuarioAusente, UsuarioDto usuarioSustituto) {
        this.usuarioAusente = usuarioAusente;
        this.usuarioSustituto = usuarioSustituto;
    }

    public UsuarioDto getUsuarioAusente() {
        return usuarioAusente;
    }

    public UsuarioDto getUsuarioSustituto() {
        return usuarioSustituto;
    }
}
