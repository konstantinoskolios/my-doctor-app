package com.example.mydoctorapp.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrescriptionDetailId implements Serializable {
    private String patientId;
    private String doctorId;
    private String prescription;
    private String date;
}
