package me.challenge.bcp.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ApiNetClient {

    @Autowired
    private RestTemplate restTemplate;
    @Value("${external.api.net.uri}")
    private String apiNetUri;

    public ApiNetResponse loadTipoCambioSunat(String fecha) {
        ResponseEntity<ApiNetResponse> responseEntity = restTemplate.exchange(
                String.format(this.apiNetUri, fecha),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ApiNetResponse>() {
                });

        return responseEntity.getBody();
    }

    public static class ApiNetResponse {
        public float compra;
        public float venta;
        public String origen;
        public String moneda;
        public String fecha;
    }

}