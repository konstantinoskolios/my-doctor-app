package org.example.model;

import java.util.List;

public record DoctorPaymentsResponse(List<PatientPaymentsDetailsResponse> paymentsList, Long totalAmount) {
}
