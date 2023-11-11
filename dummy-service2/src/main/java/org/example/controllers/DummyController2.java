package org.example.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ds2")
public class DummyController2 {

    @GetMapping("/hello")
    public String helloWorld() {
        return "Hello world from another microservice2";
    }
}
