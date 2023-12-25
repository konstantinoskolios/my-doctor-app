package org.example.config;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@AllArgsConstructor
@Slf4j
public class WebClientConfig {

//    @Bean
//    public WebClient webClient(ReactiveClientRegistrationRepository clientRegistrations,
//                               ServerOAuth2AuthorizedClientRepository authorizedClients) {
//        ServerOAuth2AuthorizedClientExchangeFilterFunction oauth = new ServerOAuth2AuthorizedClientExchangeFilterFunction(
//                clientRegistrations, authorizedClients);
//        oauth.setDefaultOAuth2AuthorizedClient(true);
//        oauth.setDefaultClientRegistrationId("AuthProvider");
//        return WebClient.builder()
//                .filter(oauth)
//                .filter(logRequest())
//                .build();
//    }
//
//    private ExchangeFilterFunction logRequest() {
//        return ExchangeFilterFunction.ofRequestProcessor(clientRequest -> {
//            log.info("Request: [{}] {}", clientRequest.method(), clientRequest.url());
//            log.debug("Payload: {}", clientRequest.body());
//            return Mono.just(clientRequest);
//        });
//    }


    @Bean
    @LoadBalanced
    public WebClient.Builder loadBalancedWebClientBuilder() {
        return WebClient.builder();
    }


}
