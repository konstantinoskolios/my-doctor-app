package org.example.model;

import java.util.List;

public record PaymentsResponse(List<PaymentsDetailsResponse> paymentsList, Long totalAmount) {
}
