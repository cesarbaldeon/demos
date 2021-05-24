package me.challenge.bcp.service;

import io.reactivex.rxjava3.core.Single;
import me.challenge.bcp.model.request.CambioRequest;
import me.challenge.bcp.model.response.CambioResponse;

public interface CambioService {
    Single<CambioResponse> cambiar(CambioRequest request);
}
