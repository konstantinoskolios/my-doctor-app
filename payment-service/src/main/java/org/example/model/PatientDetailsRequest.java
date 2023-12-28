package org.example.model;

import java.util.List;

public record PatientDetailsRequest(List<String> patientsId) {
}
