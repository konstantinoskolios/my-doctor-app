package org.example.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;


@RestController
@RequestMapping(value = "/api/ds")
@RequiredArgsConstructor
public class DummyController {

    private final WebClient.Builder webClientBuilder;

    @GetMapping("/hello")
    public String helloWorld(@RequestHeader("Authorization") String authorizationToken) {
        return webClientBuilder.build()
                .get()
                .uri("http://api-gateway/api/ds2/hello")
                .header("Authorization", authorizationToken)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }


    @GetMapping("/bye")
    public String byeWorld(@RequestHeader("Authorization") String authorizationToken) {
        return "nice!\n".concat(authorizationToken);
    }
}
