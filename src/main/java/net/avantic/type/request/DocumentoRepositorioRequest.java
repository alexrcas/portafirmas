package net.avantic.type.request;

public class DocumentoRepositorioRequest {

    private String uriDocumento;
    private String dniUsuario;

    public DocumentoRepositorioRequest(String uriDocumento, String dniUsuario) {
        this.uriDocumento = uriDocumento;
        this.dniUsuario = dniUsuario;
    }

    public String getUriDocumento() {
        return uriDocumento;
    }

    public void setUriDocumento(String uriDocumento) {
        this.uriDocumento = uriDocumento;
    }

    public String getDniUsuario() {
        return dniUsuario;
    }

    public void setDniUsuario(String dniUsuario) {
        this.dniUsuario = dniUsuario;
    }
}
