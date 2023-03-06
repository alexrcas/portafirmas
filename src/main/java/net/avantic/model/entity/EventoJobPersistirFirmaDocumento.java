package net.avantic.model.entity;

import net.avantic.model.type.EstadoEvento;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
public class EventoJobPersistirFirmaDocumento extends EventoJob {

    private DocumentoFirmado documentoFirmado;

    protected EventoJobPersistirFirmaDocumento() {
    }

    public EventoJobPersistirFirmaDocumento(LocalDateTime fechaCreacion,
                                            LocalDateTime fechaProximoReintento,
                                            EstadoEvento estadoEvento,
                                            Long numeroMaximoIntentos,
                                            Long intentos,
                                            DocumentoFirmado documentoFirmado,
                                            String respuestaServidor) {
        super(fechaCreacion, fechaProximoReintento, estadoEvento, numeroMaximoIntentos, intentos, respuestaServidor);
        this.documentoFirmado = documentoFirmado;
    }

    @ManyToOne
    @JoinColumn(name = "idDocumentoFirmado")
    public DocumentoFirmado getDocumentoFirmado() {
        return documentoFirmado;
    }

    public void setDocumentoFirmado(DocumentoFirmado documentoFirmado) {
        this.documentoFirmado = documentoFirmado;
    }
}
