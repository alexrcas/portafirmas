package net.avantic.model.dto;

public class LineaDocumentoPendienteDto {

    private long idTarea;
    private boolean isValidacion;
    private String nombre;
    private String uuid;
    private String descripcion;
    private boolean isInCircuito;
    private CircuitoDto circuitoDto;
    private boolean isSustituto;
    private boolean isDelegado;
    private boolean isUsuarioOriginal;

    public LineaDocumentoPendienteDto(long idTarea, boolean isValidacion, String nombre, String uuid, String descripcion,
                                      boolean isInCircuito, CircuitoDto circuitoDto, boolean isSustituto,
                                      boolean isDelegado, boolean isUsuarioOriginal) {
        this.idTarea = idTarea;
        this.isValidacion = isValidacion;
        this.nombre = nombre;
        this.uuid = uuid;
        this.descripcion = descripcion;
        this.isInCircuito = isInCircuito;
        this.circuitoDto = circuitoDto;
        this.isSustituto = isSustituto;
        this.isDelegado = isDelegado;
        this.isUsuarioOriginal = isUsuarioOriginal;
    }

    public long getIdTarea() {
        return idTarea;
    }

    public void setIdTarea(long idTarea) {
        this.idTarea = idTarea;
    }

    public boolean isValidacion() {
        return isValidacion;
    }

    public void setValidacion(boolean validacion) {
        isValidacion = validacion;
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

    public void setInCircuito(boolean inCircuito) {
        isInCircuito = inCircuito;
    }

    public CircuitoDto getCircuitoDto() {
        return circuitoDto;
    }

    public void setCircuitoDto(CircuitoDto circuitoDto) {
        this.circuitoDto = circuitoDto;
    }

    public boolean isSustituto() {
        return isSustituto;
    }

    public void setSustituto(boolean sustituto) {
        isSustituto = sustituto;
    }

    public boolean isDelegado() {
        return isDelegado;
    }

    public void setDelegado(boolean delegado) {
        isDelegado = delegado;
    }

    public boolean isUsuarioOriginal() {
        return isUsuarioOriginal;
    }

    public void setUsuarioOriginal(boolean usuarioOriginal) {
        isUsuarioOriginal = usuarioOriginal;
    }
}
