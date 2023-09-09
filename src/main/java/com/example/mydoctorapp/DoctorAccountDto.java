package com.example.mydoctorapp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorAccountDto {
    private String firstName;
    private String lastName;
    private String speciality;
}
