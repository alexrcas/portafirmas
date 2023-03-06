package net.avantic.repositorio.dto;

public class Credenciales {

    private String apiKey;
    private String cliente;
    private String espacio;
    private String usuario;
    private String grupo;

    public Credenciales(String apiKey, String cliente, String espacio, String usuario, String grupo) {
        this.apiKey = apiKey;
        this.cliente = cliente;
        this.espacio = espacio;
        this.usuario = usuario;
        this.grupo = grupo;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getEspacio() {
        return espacio;
    }

    public void setEspacio(String espacio) {
        this.espacio = espacio;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

}
