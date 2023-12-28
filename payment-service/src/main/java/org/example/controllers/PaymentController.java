package org.example.controllers;

import lombok.RequiredArgsConstructor;
import org.example.model.PaymentRequest;
import org.example.model.PaymentsResponse;
import org.example.service.PaymentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @GetMapping("/retrieve")
    public PaymentsResponse retrievePayments(@RequestParam String doctorId,  @RequestHeader("Authorization") String token) {
        return paymentService.retrievePayments(doctorId,token);
    }

    @PostMapping("/add")
    public void addPayment(@RequestBody PaymentRequest paymentRequest) {
        paymentService.addPayment(paymentRequest);
    }

}


