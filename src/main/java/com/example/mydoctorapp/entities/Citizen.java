package com.example.mydoctorapp.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "citizen")
public class Citizen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
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
}
