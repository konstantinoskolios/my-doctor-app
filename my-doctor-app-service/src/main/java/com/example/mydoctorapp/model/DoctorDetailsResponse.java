package com.example.mydoctorapp.model;

import java.util.Map;

public record DoctorDetailsResponse(Map<String,DoctorInformation> doctorInformationMap) {
}
