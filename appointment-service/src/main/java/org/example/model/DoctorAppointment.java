package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DoctorAppointment {
    private Long appointmentId;
    private String fullName;
    private String socialSecurityNumber;
    private String phoneNumber;
    private String appointmentDate;
    private String status;
}
