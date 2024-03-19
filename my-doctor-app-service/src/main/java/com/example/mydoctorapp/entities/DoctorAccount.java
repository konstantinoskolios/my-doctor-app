package com.example.mydoctorapp.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
    @JsonManagedReference
    @ToString.Exclude
    private List<DoctorPatientRelationship> patientRelationships = new ArrayList<>();

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
