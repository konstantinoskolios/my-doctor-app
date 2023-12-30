package com.example.mydoctorapp.model;

public record AppointmentRequest(String patientId, String doctorId, String selectedDate) {
}
