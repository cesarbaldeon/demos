package me.challenge.bcp.controller;

import io.reactivex.rxjava3.core.Single;
import me.challenge.bcp.model.request.CambioRequest;
import me.challenge.bcp.model.response.CambioResponse;
import me.challenge.bcp.service.CambioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/cambio")
public class CambioController {

    @Autowired
    private CambioService cambioService;

    @PostMapping("/operacion")
    public Single<CambioResponse> operacion(@RequestBody CambioRequest request) {
        return cambioService.cambiar(request);
    }

}
