package me.challenge.bcp.model.request;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class TipoCambioRequest implements Serializable {
    private Double monto;
    private String monedaOrigen;
    private String monedaDestino;
    private Double tipocambio;
}
