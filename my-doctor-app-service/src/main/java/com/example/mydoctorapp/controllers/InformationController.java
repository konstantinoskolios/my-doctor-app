package com.example.mydoctorapp.controllers;

import com.example.mydoctorapp.model.DoctorDetailsResponse;
import com.example.mydoctorapp.model.PatientDetailsResponse;
import com.example.mydoctorapp.services.InformationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Helper controller in order to serve data such as user details,information only for backend - backend
 */
@RestController
@RequestMapping("/api/doctor-app/info")
@RequiredArgsConstructor
public class InformationController {

    private final InformationService informationService;

    @PostMapping("/patient/retrieve-patient-info")
    public PatientDetailsResponse retrievePatientsDetail(@RequestHeader("Authorization") String token, @RequestBody List<String> patientsId) {
        return informationService.retrievePatientsDetail(patientsId);
    }

    @PostMapping("/doctor/retrieve-doctor-info")
    public DoctorDetailsResponse retrieveDoctorDetails(@RequestHeader("Authorization") String token, @RequestBody List<String> doctorsId) {
        return informationService.retrieveDoctorsDetails(doctorsId);
    }
}
