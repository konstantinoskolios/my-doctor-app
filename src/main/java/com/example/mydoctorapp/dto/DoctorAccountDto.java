package com.example.mydoctorapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorAccountDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String speciality;
}
