package org.example.model;

public record DoctorAppointment(Long appointmentId, String fullName, String socialSecurityNumber, String phoneNumber,
                                String appointmentDate, String status) {}
