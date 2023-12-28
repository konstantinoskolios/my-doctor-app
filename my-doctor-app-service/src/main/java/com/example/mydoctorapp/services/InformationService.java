package com.example.mydoctorapp.services;

import com.example.mydoctorapp.repositories.PatientAccountRepository;
import lombok.RequiredArgsConstructor;
import model.PatientDetailsResponse;
import model.PatientInformation;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InformationService {

    private final PatientAccountRepository patientAccountRepository;

    public PatientDetailsResponse retrievePatientsDetail(List<String> patientsId) {
        var set = new HashSet<>(patientsId);
        patientsId.clear();
        patientsId.addAll(set); //filter duplicate patientsId to reduce db calls
        var patientAccounts = patientAccountRepository.findAllById(patientsId);
        var patientInformation = new HashMap<String, PatientInformation>();
        patientAccounts.forEach(
                data -> patientInformation.put(data.getId(), new PatientInformation(
                        data.getFirstName().concat(" ").concat(data.getLastName()),
                        data.getSocialSecurityNumber(),
                        data.getTaxNumber(),
                        data.getPhoneNumber()
                ))
        );

        return new PatientDetailsResponse(patientInformation);
    }

}
