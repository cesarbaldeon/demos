package me.challenge.bcp.controller;

import io.reactivex.rxjava3.core.Single;
import me.challenge.bcp.model.response.MonedaResponse;
import me.challenge.bcp.service.MonedaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/monedas")
public class MonedaController {

    @Autowired
    private MonedaService monedaService;

    @GetMapping
    public Single<List<MonedaResponse>> get() {
        return monedaService.list();
    }

}
