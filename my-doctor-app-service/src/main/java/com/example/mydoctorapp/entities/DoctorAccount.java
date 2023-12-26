package com.example.mydoctorapp.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "doctor_account")
public class DoctorAccount {
    @Id
    @Column(unique = true, nullable = false)
    private String id;
    @Column(unique = true, nullable = false)
    private String email;
    private String fullName;
    private String speciality;
}
