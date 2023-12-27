package org.example.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/notification")
@RequiredArgsConstructor
public class PaymentController {

    @GetMapping("/retrieve")
    public String test() {
        return "";
    }

}
