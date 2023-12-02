package org.example.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
@RequestMapping("/api/ds2")
@RequiredArgsConstructor
@Slf4j
public class DummyController2 {

    private final WebClient.Builder webClientBuilder;

    @GetMapping("/hello")
    public String helloWorld(@RequestHeader("Authorization") String authorizationToken) {
        return webClientBuilder.build()
                .get()
                .uri("http://api-gateway/api/ds/bye")
                .header("Authorization", authorizationToken)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
