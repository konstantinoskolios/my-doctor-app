package com.example.mydoctorapp.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Citizen {
    private String first;
    private String last;
    private String afm;
    private String amka;
    private String phone;
}
