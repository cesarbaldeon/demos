package me.challenge.bcp.service;

import io.reactivex.rxjava3.core.Single;
import me.challenge.bcp.domain.CambioEntity;
import me.challenge.bcp.domain.TipoCambioEntity;
import me.challenge.bcp.model.request.CambioRequest;
import me.challenge.bcp.model.response.CambioResponse;
import me.challenge.bcp.repository.CambioRepository;
import me.challenge.bcp.repository.TipoCambioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CambioServiceImp implements CambioService {

    @Autowired
    private TipoCambioRepository tipoCambioRepository;
    @Autowired
    private CambioRepository cambioRepository;

    @Override
    public Single<CambioResponse> cambiar(CambioRequest request) {
        return Single.create(singleSubscriber -> {
            final TipoCambioEntity tipoCambioEntity = this.tipoCambioRepository.findByMoneda(request.getMonedaOrigen(), request.getMonedaDestino());
            final CambioEntity entity = new CambioEntity();
            entity.setMonedaOrigen(request.getMonedaOrigen());
            entity.setTipoCambio(tipoCambioEntity.getTipocambio());
            entity.setMonedaDestino(request.getMonedaDestino());
            entity.setMontoOrigen(request.getMonto());
            entity.setMontoDestino(tipoCambioEntity.getTipocambio() * request.getMonto());
            entity.setProvider(tipoCambioEntity.getProvider());
            entity.setFechaOperacion(new Date());
            this.cambioRepository.save(entity);
            singleSubscriber.onSuccess(CambioResponse.builder().montoOrigen(entity.getMontoOrigen())
                    .monedaOrigen(entity.getMonedaOrigen())
                    .montoDestino(entity.getMontoDestino())
                    .monedaDestino(entity.getMonedaDestino())
                    .provider(tipoCambioEntity.getProvider())
                    .tipoCambio(entity.getTipoCambio())
                    .build());
        });
    }
}
