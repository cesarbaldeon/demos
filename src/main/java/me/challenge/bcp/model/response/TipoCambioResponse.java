package me.challenge.bcp.model.response;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class TipoCambioResponse implements Serializable {
    private String monedaOrigen;
    private String monedaDestino;
    private Double tipoCambio;
    private String provider;
}
