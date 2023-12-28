package org.example.model;

public record PaymentsDetailsResponse (String fullName, String taxNumber, String socialSecurityNumber, String phoneNumber, String date, String amount) {
}
