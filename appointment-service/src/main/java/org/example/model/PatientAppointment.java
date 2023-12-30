package org.example.model;

public record PatientAppointment(Long appointmentId, String fullName, String email, String speciality, String appointmentDate, String status) {}
