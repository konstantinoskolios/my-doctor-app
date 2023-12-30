package org.example.model;

public record AppointmentRequest(String patientId,String doctorId, String selectedDate) {
}
