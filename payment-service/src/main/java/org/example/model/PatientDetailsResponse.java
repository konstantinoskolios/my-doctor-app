package org.example.model;

import java.util.Map;

public record PatientDetailsResponse(Map<String,PatientInformation> patientInformationMap) {}