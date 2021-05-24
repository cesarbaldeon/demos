package me.challenge.bcp.model.response;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class CambioResponse implements Serializable {
    
    private String monedaOrigen;
    private String monedaDestino;
    private Double montoOrigen;
    private Double montoDestino;
    private String provider;
    private Double tipoCambio;

}
