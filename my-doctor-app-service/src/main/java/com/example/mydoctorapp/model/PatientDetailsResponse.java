package com.example.mydoctorapp.model;

import java.util.Map;

public record PatientDetailsResponse(Map<String, PatientInformation> patientInformationMap) {}