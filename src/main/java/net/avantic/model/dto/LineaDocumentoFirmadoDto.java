package net.avantic.model.dto;

public class LineaDocumentoFirmadoDto {

    private Long idTarea;
    private String nombre;
    private String uuid;
    private String descripcion;
    private boolean isInCircuito;
    private CircuitoDto circuitoDto;
    private boolean isSustitucion;
    private UsuarioDto usuarioDto;
    private UsuarioDto sustituto;

    public LineaDocumentoFirmadoDto(Long idTarea, String nombre, String uuid, String descripcion,
                                    boolean isInCircuito, CircuitoDto circuitoDto, boolean isSustitucion,
                                    UsuarioDto usuario, UsuarioDto sustituto) {
        this.idTarea = idTarea;
        this.nombre = nombre;
        this.uuid = uuid;
        this.descripcion = descripcion;
        this.isInCircuito = isInCircuito;
        this.circuitoDto = circuitoDto;
        this.isSustitucion = isSustitucion;
        this.usuarioDto = usuario;
        this.sustituto = sustituto;
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

    public boolean isInCircuito() {
        return isInCircuito;
    }

    public CircuitoDto getCircuitoDto() {
        return circuitoDto;
    }

    public boolean isSustitucion() {
        return isSustitucion;
    }

    public UsuarioDto getUsuarioDto() {
        return usuarioDto;
    }

    public UsuarioDto getSustituto() {
        return sustituto;
    }
}
