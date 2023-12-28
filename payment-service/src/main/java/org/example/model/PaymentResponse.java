package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.entities.Payment;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentResponse {
    private List<Payment> paymentsList;
    private Long totalAmountSum;
}
