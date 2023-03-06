package net.avantic.type.request;

public class DocumentoRepositorioCircuitoRequest {

    private String uriDocumento;
    private String idCircuito;

    public DocumentoRepositorioCircuitoRequest(String uriDocumento, String idCircuito) {
        this.uriDocumento = uriDocumento;
        this.idCircuito = idCircuito;
    }

    public String getUriDocumento() {
        return uriDocumento;
    }

    public void setUriDocumento(String uriDocumento) {
        this.uriDocumento = uriDocumento;
    }

    public String getIdCircuito() {
        return idCircuito;
    }

    public void setIdCircuito(String idCircuito) {
        this.idCircuito = idCircuito;
    }
}
