package com.example.mydoctorapp.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PrescriptionDetailId implements Serializable {
    private Long patientId;
    private Long doctorId;
}
