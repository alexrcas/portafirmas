package net.avantic.repositorio.dto;

public class DocumentoRepositorioDto {

    private String nombreOriginal;
    private String descripcion;

    public DocumentoRepositorioDto(String nombreOriginal, String descripcion) {
        this.nombreOriginal = nombreOriginal;
        this.descripcion = descripcion;
    }

    public String getNombreOriginal() {
        return nombreOriginal;
    }

    public void setNombreOriginal(String nombreOriginal) {
        this.nombreOriginal = nombreOriginal;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
