package com.example.mydoctorapp.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.reactive.function.client.WebClient;

import static com.example.mydoctorapp.constants.Constants.MAIN_TEMPLATE_VALUE;

@Controller
@RequiredArgsConstructor
@Slf4j
public class GenericController {

    private final WebClient webClient;

    @GetMapping("/")
    public String userAdminPage() {
        return MAIN_TEMPLATE_VALUE;
    }

    @GetMapping("/helloWorld")
    public void helloWorld(){
       var result = webClient.get()
               .uri("http://dummmy-service/dummy/test")
               .retrieve()
               .bodyToMono(String.class).block();

       log.info(result);
    }
}
