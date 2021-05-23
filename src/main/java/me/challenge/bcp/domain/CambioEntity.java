package me.challenge.bcp.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "operacion_cambiaria")
public class CambioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "monedaorigen")
    private String monedaOrigen;
    @Column(name = "monedadestino")
    private String monedaDestino;
    @Column(name = "montoorigen")
    private Double montoOrigen;
    @Column(name = "montodestino")
    private Double montoDestino;
    @Column(name = "tipo_cambio")
    private Double tipoCambio;
    @Column(name = "provider")
    private String provider;
    @Column(name = "fecha_operacion")
    private Date fechaOperacion;

    public CambioEntity() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMonedaOrigen() {
        return monedaOrigen;
    }

    public void setMonedaOrigen(String monedaOrigen) {
        this.monedaOrigen = monedaOrigen;
    }

    public String getMonedaDestino() {
        return monedaDestino;
    }

    public void setMonedaDestino(String monedaDestino) {
        this.monedaDestino = monedaDestino;
    }

    public Double getMontoOrigen() {
        return montoOrigen;
    }

    public void setMontoOrigen(Double montoOrigen) {
        this.montoOrigen = montoOrigen;
    }

    public Double getMontoDestino() {
        return montoDestino;
    }

    public void setMontoDestino(Double montoDestino) {
        this.montoDestino = montoDestino;
    }

    public Double getTipoCambio() {
        return tipoCambio;
    }

    public void setTipoCambio(Double tipoCambio) {
        this.tipoCambio = tipoCambio;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public Date getFechaOperacion() {
        return fechaOperacion;
    }

    public void setFechaOperacion(Date fechaOperacion) {
        this.fechaOperacion = fechaOperacion;
    }

    @Override
    public String toString() {
        return "CambioEntity{" +
                "id=" + id +
                ", monedaOrigen='" + monedaOrigen + '\'' +
                ", monedaDestino='" + monedaDestino + '\'' +
                ", montoOrigen=" + montoOrigen +
                ", montoDestino=" + montoDestino +
                ", tipoCambio=" + tipoCambio +
                ", fechaOperacion=" + fechaOperacion +
                '}';
    }
}