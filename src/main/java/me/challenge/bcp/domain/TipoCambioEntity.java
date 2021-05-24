package me.challenge.bcp.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tipos_cambios")
public class TipoCambioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "provider")
    private String provider;
    @Column(name = "monedaorigen")
    private String monedaOrigen;
    @Column(name = "monedadestino")
    private String monedaDestino;
    @Column(name = "tipocambio")
    private Double tipocambio;
    @Column(name = "fechacarga")
    private Date fechaCarga;

    public TipoCambioEntity() {
    }

    public TipoCambioEntity(String provider, String monedaOrigen, String monedaDestino, Double tipoCambio, Date fechaCarga) {
        this.provider = provider;
        this.monedaOrigen = monedaOrigen;
        this.monedaDestino = monedaDestino;
        this.tipocambio = tipoCambio;
        this.fechaCarga = fechaCarga;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
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

    public Double getTipocambio() {
        return tipocambio;
    }

    public void setTipocambio(Double tipoCambio) {
        this.tipocambio = tipoCambio;
    }

    public Date getFechaCarga() {
        return fechaCarga;
    }

    public void setFechaCarga(Date fechaCarga) {
        this.fechaCarga = fechaCarga;
    }

    @Override
    public String toString() {
        return "TipoCambioEntity{" +
                "id=" + id +
                ", provider='" + provider + '\'' +
                ", monedaOrigen='" + monedaOrigen + '\'' +
                ", monedaDestino='" + monedaDestino + '\'' +
                ", tipoCambio=" + tipocambio +
                ", fechaCarga=" + fechaCarga +
                '}';
    }
}