package net.avantic.model.dto;

public class LineaDocumentoRechazadoDto {

    private Long idTarea;
    private String nombre;
    private String uuid;
    private String descripcion;
    private UsuarioDto usuarioDto;
    private UsuarioDto sustituto;
    private String motivoRechazo;
    private boolean isInCircuito;
    private CircuitoDto circuitoDto;
    private boolean isSustitucion;

    public LineaDocumentoRechazadoDto(Long idTarea, String nombre, String uuid, String descripcion, UsuarioDto usuarioDto, UsuarioDto sustituto, String motivoRechazo,
                                      boolean isInCircuito, CircuitoDto circuitoDto, boolean isSustitucion) {
        this.idTarea = idTarea;
        this.nombre = nombre;
        this.uuid = uuid;
        this.descripcion = descripcion;
        this.usuarioDto = usuarioDto;
        this.sustituto = sustituto;
        this.motivoRechazo = motivoRechazo;
        this.isInCircuito = isInCircuito;
        this.circuitoDto = circuitoDto;
        this.isSustitucion = isSustitucion;
    }

    public Long getIdTarea() {
        return idTarea;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public UsuarioDto getUsuarioDto() {
        return usuarioDto;
    }

    public void setUsuarioDto(UsuarioDto usuarioDto) {
        this.usuarioDto = usuarioDto;
    }

    public UsuarioDto getSustituto() {
        return sustituto;
    }

    public String getMotivoRechazo() {
        return motivoRechazo;
    }

    public void setMotivoRechazo(String motivoRechazo) {
        this.motivoRechazo = motivoRechazo;
    }

    public boolean isInCircuito() {
        return isInCircuito;
    }

    public CircuitoDto getCircuitoDto() {
        return circuitoDto;
    }

    public boolean isSustitucion() {
        return isSustitucion;
    }
}
