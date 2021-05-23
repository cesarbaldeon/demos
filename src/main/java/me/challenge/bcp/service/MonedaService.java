package me.challenge.bcp.service;

import io.reactivex.rxjava3.core.Single;
import me.challenge.bcp.model.response.MonedaResponse;

import java.io.IOException;
import java.util.List;

public interface MonedaService {

    void cargarMonedasGlobales() throws IOException;
    Single<List<MonedaResponse>> list();
}
