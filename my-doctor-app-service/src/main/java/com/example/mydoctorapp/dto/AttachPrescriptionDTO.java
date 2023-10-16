package com.example.mydoctorapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
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
