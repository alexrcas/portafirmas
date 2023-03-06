package net.avantic.model.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;
import java.sql.Clob;

@Entity
public class DocumentoFirmado extends PanacheEntityBase {

    private Long id;
    private String firma;
    private TareaFirma tareaFirma;

    protected DocumentoFirmado() {
    }

    public DocumentoFirmado(TareaFirma tareaFirma, String firma) {
        this.tareaFirma = tareaFirma;
        //todo arodriguez: solo para pruebas
        this.firma = firma.substring(0, 64);
    }

    private void setId(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public String getFirma() {
        return firma;
    }

    public void setFirma(String firma) {
        this.firma = firma;
    }

    @ManyToOne
    @JoinColumn(name = "idTareaFirma")
    public Tarea getTareaFirma() {
        return tareaFirma;
    }

    public void setTareaFirma(TareaFirma tareaFirma) {
        this.tareaFirma = tareaFirma;
    }
}
