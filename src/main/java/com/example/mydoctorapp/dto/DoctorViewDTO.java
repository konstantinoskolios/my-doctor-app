package com.example.mydoctorapp.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class DoctorViewDTO {
    @NotBlank
    private Long patientId;
    @NotBlank
    private Long doctorId;
    private String comment;
    private String patientFullName;
}
