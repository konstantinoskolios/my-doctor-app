package org.example.model;

public record PatientPaymentsDetailsResponse(String fullName, String taxNumber, String socialSecurityNumber, String phoneNumber, String date, String amount) {
}
