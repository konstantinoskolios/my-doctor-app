package com.example.mydoctorapp.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.example.mydoctorapp.utils.MyDoctorAppUtils.getCurrentTimeInGMT3OnlyDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "patient_account")
@Builder
public class PatientAccount {
    @Id
    @Column(unique = true, nullable = false)
    private String id;
    private String firstName;
    private String lastName;
    private String fatherFirstName;
    @Column(unique = true)
    private String taxNumber;
    @Column(unique = true)
    private String socialSecurityNumber;
    @Column(unique = true)
    private String phoneNumber;
    private String birthdate;
    private String comments;
    private String createdDate = getCurrentTimeInGMT3OnlyDate();
    @Column(nullable = false)
    private String doctorId;

}
