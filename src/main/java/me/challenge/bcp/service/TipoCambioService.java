package me.challenge.bcp.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.reactivex.rxjava3.core.Single;
import me.challenge.bcp.model.request.TipoCambioRequest;
import me.challenge.bcp.model.response.TipoCambioResponse;

public interface TipoCambioService {
    void cargarTiposCambioGlobal() throws JsonProcessingException;

    Single<TipoCambioResponse> getTipoCambioSunat();
    Single<TipoCambioResponse> getTipoCambio(TipoCambioRequest tipoCambioRequest);

    Single<TipoCambioResponse> actualizarTipoCambio(TipoCambioRequest tipoCambioRequest);
}
