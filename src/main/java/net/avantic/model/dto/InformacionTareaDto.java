package net.avantic.model.dto;

import net.avantic.type.EstadoTarea;

public class InformacionTareaDto {

    private UsuarioDto usuarioDto;
    private EstadoTarea estado;
    private boolean isAusente;
    private UsuarioDto sustituto;
    private boolean isFirmaSustituto;
    private UsuarioDto firmadoSustituto;
    private boolean isDelegado;
    private UsuarioDto usuarioDelegado;

    public InformacionTareaDto(UsuarioDto usuarioDto, EstadoTarea estado,
                               boolean isAusente, UsuarioDto sustituto,
                               boolean isFirmaSustituto, UsuarioDto firmadoSustituto,
                               boolean isDelegado, UsuarioDto usuarioDelegado) {
        this.usuarioDto = usuarioDto;
        this.estado = estado;
        this.isAusente = isAusente;
        this.sustituto = sustituto;
        this.isFirmaSustituto = isFirmaSustituto;
        this.firmadoSustituto = firmadoSustituto;
        this.isDelegado = isDelegado;
        this.usuarioDelegado = usuarioDelegado;
    }

    public UsuarioDto getUsuarioDto() {
        return usuarioDto;
    }

    public EstadoTarea getEstado() {
        return estado;
    }

    public boolean isAusente() {
        return isAusente;
    }

    public UsuarioDto getSustituto() {
        return sustituto;
    }

    public boolean isFirmaSustituto() {
        return isFirmaSustituto;
    }

    public UsuarioDto getFirmadoSustituto() {
        return firmadoSustituto;
    }

    public boolean isDelegado() {
        return isDelegado;
    }

    public UsuarioDto getUsuarioDelegado() {
        return usuarioDelegado;
    }
}
