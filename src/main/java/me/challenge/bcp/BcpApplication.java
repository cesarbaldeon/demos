package me.challenge.bcp;

import me.challenge.bcp.service.MonedaService;
import me.challenge.bcp.service.TipoCambioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableAutoConfiguration
public class BcpApplication {

    public static void main(String[] args) {
        SpringApplication.run(BcpApplication.class, args);
    }

    @Configuration
    @EnableScheduling
    public class RestTemplateConfig {
        @Bean
        public RestTemplate restTemplate(RestTemplateBuilder builder) {
            return builder.build();
        }
    }

    @Component
    public class ScheduleTask {

        @Autowired
        private TipoCambioService tipoCambioService;
        @Autowired
        private MonedaService monedaService;

        @EventListener(ApplicationReadyEvent.class)
        public void onApplicationReady() {
            try {
                this.monedaService.cargarMonedasGlobales();
                this.tipoCambioService.cargarTiposCambioGlobal();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        @Scheduled(cron = "0 0 * * * ?", zone = "America/Lima")
        public void scheduleTaskUpdateConvertionRates() {
            try {
                this.tipoCambioService.cargarTiposCambioGlobal();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

    }

}
