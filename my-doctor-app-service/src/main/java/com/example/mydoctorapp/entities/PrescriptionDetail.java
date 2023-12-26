package com.example.mydoctorapp.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "prescription_detail")
@Builder
@IdClass(PrescriptionDetailId.class)
public class PrescriptionDetail {
    @Id
    private String patientId;
    @Id
    private String doctorId;
    private String category;
    @Id
    private String prescription;
    @Id
    private String date;
}
