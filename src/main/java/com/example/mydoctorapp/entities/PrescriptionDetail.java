package com.example.mydoctorapp.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "prescription_detail")
@Builder
@IdClass(PrescriptionDetailId.class)
public class PrescriptionDetail {
    @Id
    private Long patientId;
    @Id
    private Long doctorId;
    private String category;
    @Id
    private String prescription;
    @Id
    private String date;
}
