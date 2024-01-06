package com.example.mydoctorapp.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

import static com.example.mydoctorapp.utils.MyDoctorAppUtils.getCurrentTimeInGMT3OnlyDate;

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
    private Date createdDate = getCurrentTimeInGMT3OnlyDate();

    public DoctorAccount(String id, String email, String fullName, String speciality) {
        this.id = id;
        this.email = email;
        this.fullName = fullName;
        this.speciality = speciality;
    }
}
