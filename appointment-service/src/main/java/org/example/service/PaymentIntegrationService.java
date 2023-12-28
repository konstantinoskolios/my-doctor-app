package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.model.PatientDetailsResponse;
import org.example.model.PaymentRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Service
@RequiredArgsConstructor
public class PaymentIntegrationService {

    private final WebClient.Builder webclientBuilder;

    public void addPayment(String token, PaymentRequest paymentRequest) {
        webclientBuilder.build()
                .post()
                .uri("http://payment-service:9999/api/payment/add")
                .header("Authorization", token)
                .contentType(APPLICATION_JSON)
                .body(BodyInserters.fromValue(paymentRequest))
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }
}
