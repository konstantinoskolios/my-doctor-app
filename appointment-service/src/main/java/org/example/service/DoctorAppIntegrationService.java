package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.model.PatientDetailsResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Service
@RequiredArgsConstructor
public class DoctorAppIntegrationService {

    private final WebClient.Builder webclientBuilder;

    public PatientDetailsResponse retrievePatientsInformation(String token, List<String> patientsId) {
        return webclientBuilder.build()
                .post()
                .uri("http://doctor-app:8500/api/doctor-app/patient/retrieve-patient-info")
                .header("Authorization", token)
                .contentType(APPLICATION_JSON)
                .body(BodyInserters.fromValue(patientsId))
                .retrieve()
                .bodyToMono(PatientDetailsResponse.class)
                .block();
    }
}
