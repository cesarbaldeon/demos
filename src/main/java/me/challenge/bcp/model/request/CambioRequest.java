package me.challenge.bcp.model.request;

import lombok.Data;

@Data
public class CambioRequest {

    private String monedaOrigen;
    private String monedaDestino;
    private Double monto;

}
