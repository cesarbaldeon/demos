package me.challenge.bcp.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class OpenExchangeRatesClient {

    @Autowired
    private RestTemplate restTemplate;
    @Value("${external.api.openexchangerates.uri}")
    private String openexchangeratesUri;
    @Value("${external.api.openexchangerates.api_key}")
    private String apiKey;
    @Value("${external.api.openexchangerates.currencies.uri}")
    private String openexchangeratesCurrenciesUri;

    public String loadCurrentExchange() {
        return restTemplate.exchange(
                String.format(this.openexchangeratesUri, apiKey),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<String>() {
                }).getBody();
    }

    public String loadCurrencies() {
        return restTemplate.exchange(
                this.openexchangeratesCurrenciesUri,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<String>() {
                }).getBody();
    }

}
