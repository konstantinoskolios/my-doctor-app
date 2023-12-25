package org.example.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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


    @GetMapping("/public/bye")
    public String byeWorld() {
        return "You logged in ";
    }

    @GetMapping("/user/bye")
    public String byeWorld2() {
        return "You logged in with role user";
    }

    @GetMapping("/admin/bye")
    public String byeWorld3() {return "You logged in with role user";}

}
