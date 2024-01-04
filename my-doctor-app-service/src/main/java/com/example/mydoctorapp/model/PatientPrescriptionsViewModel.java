package com.example.mydoctorapp.model;

public record PatientPrescriptionsViewModel(
        String doctorFullName,
        String doctorEmail,
        String prescription,
        String category,
        String date
) {
}
