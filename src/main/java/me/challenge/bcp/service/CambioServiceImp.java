package me.challenge.bcp.service;

import io.reactivex.rxjava3.core.Single;
import me.challenge.bcp.domain.CambioEntity;
import me.challenge.bcp.model.request.CambioRequest;
import me.challenge.bcp.model.request.TipoCambioRequest;
import me.challenge.bcp.model.response.CambioResponse;
import me.challenge.bcp.model.response.TipoCambioResponse;
import me.challenge.bcp.repository.CambioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CambioServiceImp implements CambioService {

    @Autowired
    private TipoCambioService tipoCambioService;
    @Autowired
    private CambioRepository cambioRepository;

    @Override
    public Single<CambioResponse> cambiar(CambioRequest request) {
        return Single.create(singleSubscriber -> {
            final TipoCambioResponse tipoCambioResponse = this.tipoCambioService.getTipoCambio(
                    TipoCambioRequest.builder().monto(request.getMonto())
                            .monedaDestino(request.getMonedaDestino())
                            .monedaOrigen(request.getMonedaOrigen())
                            .build()).blockingGet();

            final CambioEntity entity = new CambioEntity();
            entity.setMonedaOrigen(request.getMonedaOrigen());
            entity.setTipoCambio(tipoCambioResponse.getTipoCambio());
            entity.setMonedaDestino(request.getMonedaDestino());
            entity.setMontoOrigen(request.getMonto());
            entity.setMontoDestino(tipoCambioResponse.getTipoCambio() * request.getMonto());
            entity.setProvider(tipoCambioResponse.getProvider());
            entity.setFechaOperacion(new Date());
            this.cambioRepository.save(entity);
            singleSubscriber.onSuccess(CambioResponse.builder().montoOrigen(entity.getMontoOrigen())
                    .monedaOrigen(entity.getMonedaOrigen())
                    .montoDestino(entity.getMontoDestino())
                    .monedaDestino(entity.getMonedaDestino())
                    .provider(tipoCambioResponse.getProvider())
                    .tipoCambio(entity.getTipoCambio())
                    .build());
        });
    }
}
