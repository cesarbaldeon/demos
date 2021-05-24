package me.challenge.bcp.domain;

import javax.persistence.*;

@Entity
@Table(name = "monedas")
public class MonedaEntity {

    @Id
    @Column(name = "code")
    private String code;
    @Column(name = "nombre")
    private String nombre;

    public MonedaEntity() {
    }

    public MonedaEntity(String code, String nombre) {
        this.code = code;
        this.nombre = nombre;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "MonedaEntity{" +
                "code='" + code + '\'' +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
