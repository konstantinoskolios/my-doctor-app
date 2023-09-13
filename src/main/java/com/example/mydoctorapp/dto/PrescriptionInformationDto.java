package com.example.mydoctorapp.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PrescriptionInformationDto {
    @NotBlank
    private String category;
    @JsonProperty("prescription")
    @NotBlank
    private String prescriptionName;
    @NotBlank
    private String date;
}
