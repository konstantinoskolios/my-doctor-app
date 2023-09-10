package com.example.mydoctorapp.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "doctor_account")
public class DoctorAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private String pass;
    private String firstName;
    private String lastName;
    private String speciality;

    public DoctorAccount(String email, String pass) {
        this.id = 1000L;
        this.email = email;
        this.pass = pass;
        this.firstName = "Nikos";
        this.lastName = "Koukos";
        this.speciality = "Pa8ologos";
    }
}
