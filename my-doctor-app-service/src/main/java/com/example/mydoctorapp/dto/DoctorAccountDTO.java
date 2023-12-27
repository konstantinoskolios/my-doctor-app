package com.example.mydoctorapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorAccountDTO {
    private String id;
    private String fullName;
    private String speciality;
}
