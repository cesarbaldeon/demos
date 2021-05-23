package me.challenge.bcp.controller;

import io.reactivex.rxjava3.core.Single;
import me.challenge.bcp.model.request.TipoCambioRequest;
import me.challenge.bcp.model.response.TipoCambioResponse;
import me.challenge.bcp.service.TipoCambioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/tipocambio")
public class TipoCambioController {

    @Autowired
    private TipoCambioService tipoCambioService;


    @GetMapping("/tipo_cambio_sunat")
    public Single<TipoCambioResponse> getTipoCambioSunat() {
        return tipoCambioService.getTipoCambioSunat();
    }

    @PostMapping(
            path = "/tipo_cambio"
    )
    public Single<TipoCambioResponse> getTipoCambio(@RequestBody TipoCambioRequest tipoCambioRequest) {
        return tipoCambioService.getTipoCambio(tipoCambioRequest);
    }

    @PostMapping(
            path = "/actualizar_tc"
    )
    public Single<TipoCambioResponse> actualizarTipoCambio(@RequestBody TipoCambioRequest tipoCambioRequest) {
        return tipoCambioService.actualizarTipoCambio(tipoCambioRequest);
    }
}