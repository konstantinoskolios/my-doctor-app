package com.example.mydoctorapp.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

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

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date createdDate;

    public DoctorAccount(String id, String email, String fullName, String speciality) {
        this.id = id;
        this.email = email;
        this.fullName = fullName;
        this.speciality = speciality;
    }

    @PrePersist
    protected void onCreate() {
        if (this.createdDate == null) {
            this.createdDate = new Date();
        }
    }
}
