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
@IdClass(PrescriptionDetailId.class)
@Builder
public class PrescriptionDetail {
    @Id
    private Long patientId;
    @Id
    private Long doctorId;
    private String category;
    private String prescriptionName;
    private String date;
}
