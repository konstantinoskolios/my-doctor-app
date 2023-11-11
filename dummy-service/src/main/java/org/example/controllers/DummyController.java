package org.example.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;

@RestController
@RequestMapping(value = "/api/ds")
public class DummyController {

    @GetMapping("hello")
    public String helloWorld(){
        return  "Hello world from another microservice";
    }
}
