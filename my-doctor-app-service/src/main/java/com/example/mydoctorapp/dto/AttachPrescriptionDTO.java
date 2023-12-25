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
    private Long doctorId;
    @NotNull
    private Long patientId;
    @NotNull
    @Valid
    private List<PrescriptionInformationDTO> prescriptionsInformation;
}
