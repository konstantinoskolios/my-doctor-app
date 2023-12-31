package org.example.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("microservice")
public record MicroserviceProperties(
        String retrievePatientsInformationUrl,
        String retrieveDoctorsInformationUrl,
        String addPaymentUrl
){
}
