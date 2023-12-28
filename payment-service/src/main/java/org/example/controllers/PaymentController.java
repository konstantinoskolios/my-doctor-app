package org.example.controllers;

import lombok.RequiredArgsConstructor;
import org.example.entities.Payment;
import org.example.model.PaymentRequest;
import org.example.model.PaymentResponse;
import org.example.service.PaymentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @GetMapping("/retrieve")
    public PaymentResponse retrievePayments(@RequestParam String doctorId) {
        var payments = paymentService.retrievePayments(doctorId);
        var paymentsTotal = payments.stream().mapToLong(Payment::getAmount).sum();
        return new PaymentResponse(payments, paymentsTotal);

    }

    @PostMapping("/add")
    public void addPayment(@RequestBody PaymentRequest paymentRequest) {
        paymentService.addPayment(paymentRequest);
    }

}


