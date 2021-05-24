package me.challenge.bcp.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.reactivex.rxjava3.core.Single;
import me.challenge.bcp.client.ApiNetClient;
import me.challenge.bcp.client.OpenExchangeRatesClient;
import me.challenge.bcp.domain.TipoCambioEntity;
import me.challenge.bcp.model.request.TipoCambioRequest;
import me.challenge.bcp.model.response.TipoCambioResponse;
import me.challenge.bcp.repository.TipoCambioRepository;
import org.apache.commons.collections4.IteratorUtils;
import org.apache.commons.math3.util.Precision;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TipoCambioServiceImpl implements TipoCambioService {

    private static final String DATE_FORMAT = "yyyy-MM-dd";

    @Autowired
    private TipoCambioRepository tipoCambioRepository;
    @Autowired
    private ApiNetClient apiNetCliente;
    @Autowired
    private OpenExchangeRatesClient openExchangeRatesClient;
    private DateFormat formatter = new SimpleDateFormat(DATE_FORMAT);

    @Override
    public void cargarTiposCambioGlobal() throws JsonProcessingException {
        final ObjectMapper objectMapper = new ObjectMapper();
        final JsonNode jsonNodeRoot = objectMapper.readTree(this.openExchangeRatesClient.loadCurrentExchange());
        final String base = jsonNodeRoot.get("base").asText();
        final JsonNode rates = jsonNodeRoot.get("rates");
        List<TipoCambioEntity> entities = IteratorUtils.toList(rates.fields()).stream().map(item -> {
            TipoCambioEntity entity = new TipoCambioEntity();
            entity.setMonedaOrigen(base);
            entity.setMonedaDestino(item.getKey());
            entity.setTipocambio(Precision.round(Double.valueOf(item.getValue().asText()), 5));
            entity.setProvider("OPEN_EXCHANGE");
            entity.setFechaCarga(new Date());
            return entity;
        }).collect(Collectors.toList());
        this.tipoCambioRepository.saveAll(entities);
    }

    @Override
    public Single<TipoCambioResponse> getTipoCambio(TipoCambioRequest tipoCambioRequest) {
        return Single.create(singleSubscriber -> {
            try {
                Optional<TipoCambioEntity> tipoCambioEntity = tipoCambioRepository.findByMonedas(
                        tipoCambioRequest.getMonedaOrigen(),tipoCambioRequest.getMonedaDestino())
                .stream().findFirst();
                if (tipoCambioEntity.isPresent()) {
                    singleSubscriber.onSuccess(
                            TipoCambioResponse.builder()
                                    .tipoCambio(tipoCambioEntity.get().getTipocambio())
                                    .provider(tipoCambioEntity.get().getProvider())
                                    .monedaOrigen(tipoCambioEntity.get().getMonedaOrigen())
                                    .monedaDestino(tipoCambioEntity.get().getMonedaDestino())
                                    .build()
                                    );
                } else {
                         singleSubscriber.onError(new EntityNotFoundException());
                        }
            } catch (Exception ex) {
                singleSubscriber.onError(new EntityNotFoundException());
            }
        });
    }

    @Override
    public Single<TipoCambioResponse> getTipoCambioSunat() {
        return Single.create(singleSubscriber -> {
            final String fechaConsulta = this.formatter.format(new Date());
            try {

                TipoCambioEntity tipoCambioSunat = this.toMap(this.apiNetCliente.loadTipoCambioSunat(fechaConsulta));
                TipoCambioEntity tipoCambioEntity = tipoCambioRepository.findByMoneda(
                         tipoCambioSunat.getMonedaOrigen(), tipoCambioSunat.getMonedaDestino());

                if (tipoCambioEntity == null) {
                    this.tipoCambioRepository.save(tipoCambioSunat);
                } else {
                    tipoCambioSunat.setId(tipoCambioEntity.getId());
                    this.tipoCambioRepository.save(tipoCambioSunat);
                }
                singleSubscriber.onSuccess(
                        TipoCambioResponse.builder()
                                .tipoCambio(tipoCambioSunat.getTipocambio())
                                .provider(tipoCambioSunat.getProvider())
                                .monedaOrigen(tipoCambioSunat.getMonedaOrigen())
                                .monedaDestino(tipoCambioSunat.getMonedaDestino())
                                .build()
                );
            } catch (Exception ex) {
                singleSubscriber.onError(new EntityNotFoundException());
            }
        });
    }


    @Override
    public Single<TipoCambioResponse> actualizarTipoCambio(TipoCambioRequest tipoCambioRequest) {
        return Single.create(singleSubscriber -> {
            final String fechaConsulta = this.formatter.format(new Date());
            try {
                TipoCambioEntity tipoCambioEntity = tipoCambioRepository.findByMoneda(
                        tipoCambioRequest.getMonedaOrigen(), tipoCambioRequest.getMonedaDestino());
                tipoCambioEntity.setTipocambio(tipoCambioRequest.getTipocambio());

                this.tipoCambioRepository.save(tipoCambioEntity);
                singleSubscriber.onSuccess(
                        TipoCambioResponse.builder()
                                .tipoCambio(tipoCambioEntity.getTipocambio())
                                .provider(tipoCambioEntity.getProvider())
                                .monedaOrigen(tipoCambioEntity.getMonedaOrigen())
                                .monedaDestino(tipoCambioEntity.getMonedaDestino())
                                .build()
                );

            } catch (Exception ex) {
                singleSubscriber.onError(new EntityNotFoundException());
            }
        });
    }


    private TipoCambioEntity toMap(ApiNetClient.ApiNetResponse response) {
        TipoCambioEntity entity = new TipoCambioEntity();
        entity.setTipocambio(Double.valueOf(response.compra));
        entity.setProvider(response.origen);
        entity.setMonedaOrigen("PEN");
        entity.setMonedaDestino(response.moneda);
        entity.setFechaCarga(new Date());
        return entity;
    }

}
