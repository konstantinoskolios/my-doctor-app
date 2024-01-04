package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.config.MicroserviceProperties;
import org.example.model.PaymentRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import static org.example.constants.Constants.AUTHORIZATION_VALUE;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@Service
@RequiredArgsConstructor
public class PaymentIntegrationService {

    private final WebClient.Builder webclientBuilder;
    private final MicroserviceProperties microserviceProperties;

    public void addPayment(String token, PaymentRequest paymentRequest) {
        webclientBuilder.build()
                .post()
                .uri(microserviceProperties.addPaymentUrl())
                .header(AUTHORIZATION_VALUE, token)
                .contentType(APPLICATION_JSON)
                .body(BodyInserters.fromValue(paymentRequest))
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }
}
