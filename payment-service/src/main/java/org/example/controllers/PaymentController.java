package org.example.controllers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/payment")
@RequiredArgsConstructor
public class PaymentController {

    @GetMapping("/retrieve")
    public PaymentResponse test() {

        var payments = List.of(
                new Payment("John Smith", "112211234", "11223312345", "444444444", "20-01-2003", 30L),
                new Payment("Peter Dang", "554433231", "22112321234", "111221123", "20-10-2024", 5L),
                new Payment("Paola ALenk", "112211234", "3341123456", "112233123", "95-05-5999", 9L)
        );
        var paymentsTotal = payments.stream().mapToLong(Payment::getTotalAmount).sum();
        return new PaymentResponse(payments,paymentsTotal);

    }

}


@Data
@AllArgsConstructor
@NoArgsConstructor
class Payment {
    private String fullName;
    private String taxNumber;
    private String socialSecurityNumber;
    private String phoneNumber;
    private String date;
    private Long totalAmount;
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class PaymentResponse {
    private List<Payment> paymentsList;
    private Long totalAmountSum;
}

