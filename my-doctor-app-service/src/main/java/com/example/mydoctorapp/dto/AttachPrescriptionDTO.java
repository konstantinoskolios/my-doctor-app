package com.example.mydoctorapp.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttachPrescriptionDTO {
    @NotNull
    private String doctorId;
    @NotNull
    private String patientId;
    @NotNull
    @Valid
    private List<PrescriptionInformationDTO> prescriptionsInformation;
}
