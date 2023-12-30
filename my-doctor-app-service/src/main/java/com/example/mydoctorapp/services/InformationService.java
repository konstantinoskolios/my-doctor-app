package com.example.mydoctorapp.services;

import com.example.mydoctorapp.model.DoctorDetailsResponse;
import com.example.mydoctorapp.model.DoctorInformation;
import com.example.mydoctorapp.model.PatientDetailsResponse;
import com.example.mydoctorapp.model.PatientInformation;
import com.example.mydoctorapp.repositories.DoctorAccountRepository;
import com.example.mydoctorapp.repositories.PatientAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InformationService {

    private final PatientAccountRepository patientAccountRepository;
    private final DoctorAccountRepository doctorAccountRepository;

    public PatientDetailsResponse retrievePatientsDetail(List<String> patientsId) {
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

    public DoctorDetailsResponse retrieveDoctorsDetails(List<String> doctorsId) {
        var doctorAccounts = doctorAccountRepository.findAllById(doctorsId);
        var doctorInformation = new HashMap<String, DoctorInformation>();
        doctorAccounts.forEach(
                data -> doctorInformation.put(data.getId(), new DoctorInformation(
                        data.getFullName(),
                        data.getEmail(),
                        data.getSpeciality()
                ))
        );
        return new DoctorDetailsResponse(doctorInformation);
    }

}
