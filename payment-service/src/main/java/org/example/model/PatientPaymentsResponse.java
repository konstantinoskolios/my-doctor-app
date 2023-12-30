package org.example.model;

import java.util.List;

public record PatientPaymentsResponse(List<DoctorPaymentsDetailsResponse> paymentsList, Long totalAmount) {
}
