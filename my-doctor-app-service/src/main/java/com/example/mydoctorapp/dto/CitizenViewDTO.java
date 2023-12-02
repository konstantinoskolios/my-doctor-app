package com.example.mydoctorapp.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CitizenViewDTO {
    @Length(max = 9, min = 9, message = "Tax number must be exactly 9 characters.")
    private String taxNumber;
    @Length(max = 10, min = 10, message = "Phone number must be exactly 10 characters.")
    private String phoneNumber;
    @Length(max = 11, min = 11, message = "Social security number must be exactly 11 characters.")
    private String socialNumber;
}
