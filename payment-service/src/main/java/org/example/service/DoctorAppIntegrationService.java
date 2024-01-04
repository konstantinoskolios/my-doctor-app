package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.config.MicroserviceProperties;
import org.example.model.DoctorDetailsResponse;
import org.example.model.PatientDetailsResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

import static org.example.constants.Constants.AUTHORIZATION_VALUE;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@Service
@RequiredArgsConstructor
public class DoctorAppIntegrationService {

    private final WebClient.Builder webclientBuilder;
    private final MicroserviceProperties microserviceProperties;
    public PatientDetailsResponse retrievePatientsInformation(String token, List<String> patientsId){
        return webclientBuilder.build()
                .post()
                .uri(microserviceProperties.retrievePatientsInformationUrl())
                .header(AUTHORIZATION_VALUE, token)
                .contentType(APPLICATION_JSON)
                .body(BodyInserters.fromValue(patientsId))
                .retrieve()
                .bodyToMono(PatientDetailsResponse.class)
                .block();
    }

    public DoctorDetailsResponse retrieveDoctorsInformation(String token, List<String> doctorsId){
        return webclientBuilder.build()
                .post()
                .uri(microserviceProperties.retrieveDoctorsInformationUrl())
                .header(AUTHORIZATION_VALUE, token)
                .contentType(APPLICATION_JSON)
                .body(BodyInserters.fromValue(doctorsId))
                .retrieve()
                .bodyToMono(DoctorDetailsResponse.class)
                .block();
    }

}
