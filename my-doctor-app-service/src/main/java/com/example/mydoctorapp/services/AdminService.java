package com.example.mydoctorapp.services;

import com.example.mydoctorapp.model.DateRangeModel;
import com.example.mydoctorapp.repositories.DoctorAccountRepository;
import com.example.mydoctorapp.repositories.PatientAccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdminService {

    private final PatientAccountRepository patientAccountRepository;
    private final DoctorAccountRepository doctorAccountRepository;

    public String getBusinessMetrics(DateRangeModel dateRangeModel) {
        var patientsAccounts = patientAccountRepository.countByCreatedDateGreaterThanEqualAndCreatedDateLessThanEqual(dateRangeModel.startDate(), dateRangeModel.endDate());
        var doctorsAccounts = doctorAccountRepository.countByCreatedDateGreaterThanEqualAndCreatedDateLessThanEqual(dateRangeModel.startDate(), dateRangeModel.endDate());
        return String.format("Total Patient Accounts: %s and Total Doctor Accounts: %s for period: [%s - %s]", patientsAccounts, doctorsAccounts, dateRangeModel.startDate(), dateRangeModel.endDate());
    }

}
