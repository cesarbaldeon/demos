package me.challenge.bcp.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.reactivex.rxjava3.core.Single;
import me.challenge.bcp.client.OpenExchangeRatesClient;
import me.challenge.bcp.domain.MonedaEntity;
import me.challenge.bcp.model.response.MonedaResponse;
import me.challenge.bcp.repository.MonedaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MonedaServiceImp implements MonedaService {

    @Autowired
    private MonedaRepository monedaRepository;
    @Autowired
    private OpenExchangeRatesClient openExchangeRatesClient;

    @Override
    public void cargarMonedasGlobales() throws IOException {
        if (monedaRepository.count() == 0) {
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, String> map = objectMapper.readValue(this.openExchangeRatesClient.loadCurrencies(), new TypeReference<Map<String, String>>() {
            });
            List<MonedaEntity> monedas = map.entrySet().stream()
                    .map(item -> new MonedaEntity(item.getKey(), item.getValue()))
                    .collect(Collectors.toList());
            this.monedaRepository.saveAll(monedas);
        }
    }

    @Override
    public Single<List<MonedaResponse>> list() {
        return Single.create(singleSubscriber -> {
            List<MonedaResponse> response =
                    this.monedaRepository.findAll().stream()
                            .map(item -> MonedaResponse.builder().code(item.getCode())
                                    .name(item.getNombre()).build()).collect(Collectors.toList());
            singleSubscriber.onSuccess(response);
        });
    }

}
