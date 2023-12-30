package com.example.mydoctorapp.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PrescriptionInformationDTO {
    @NotBlank
    private String category;
    @JsonProperty("prescription")
    @NotBlank
    private String prescriptionName;
    @NotBlank
    private String date;
    private String doctorInformation;
}
