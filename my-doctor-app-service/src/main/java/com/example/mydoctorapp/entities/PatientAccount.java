package com.example.mydoctorapp.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


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
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Column(name = "created_date", nullable = false, updatable = false)
    private Date createdDate;
    @Column(nullable = false)
    private String doctorId;

    @PrePersist
    protected void onCreate() {
        if (this.createdDate == null) {
            this.createdDate = new Date();
        }
    }

}
