package com.example.mydoctorapp.services;

import com.example.mydoctorapp.dto.CitizenViewDTO;
import com.example.mydoctorapp.entities.Citizen;
import com.example.mydoctorapp.exceptions.InvalidCredentialsException;
import com.example.mydoctorapp.mapstruct.PrescriptionMapper;
import com.example.mydoctorapp.repositories.CitizenRepository;
import com.example.mydoctorapp.repositories.DoctorAccountRepository;
import com.example.mydoctorapp.repositories.PrescriptionDetailRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.stream.Collectors;

import static com.example.mydoctorapp.constants.Constants.*;
import static com.example.mydoctorapp.specifications.CitizenSpecification.constructCitizenSpecification;

@Service
@Slf4j
@RequiredArgsConstructor
public class CitizenService {


    private final CitizenRepository citizenRepository;
    private final PrescriptionDetailRepository prescriptionDetailRepository;
    private final PrescriptionMapper prescriptionMapper;
    private final DoctorAccountRepository doctorAccountRepository;

    public String getAllCitizens(int page, int size, String sortField, String sortDir, Model model, String searchBy) {
        var pageable = PageRequest.of(page, size, sortDir.equals(SORT_ASCENDING) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending());
        var citizens = citizenRepository.findAll(constructCitizenSpecification(searchBy), pageable);
        displayItems(citizens, page, model);
        model.addAttribute(CITIZENS, citizens);
        return CITIZENS_TEMPLATE_VALUE;
    }

    private void displayItems(Page<Citizen> citizens, int page, Model model) {
        int startItem = Math.min((int) citizens.getTotalElements(), page * citizens.getSize() + ONE_VALUE);
        int endItem = Math.min((startItem + citizens.getSize() - ONE_VALUE), (int) citizens.getTotalElements());
        model.addAttribute(START_ITEM, startItem);
        model.addAttribute(END_ITEM, endItem);
    }

    public String getCitizenInformation(CitizenViewDTO citizenViewDTO, Model model) {
        try {
            var patient = citizenRepository.findByTaxNumberAndSocialSecurityNumber(citizenViewDTO.getTaxNumber(), citizenViewDTO.getSocialNumber()).orElseThrow(
                    () -> {
                        var errorMessage = String.format("Invalid login credentials for the citizen with tax number: %s and social security number: %s", citizenViewDTO.getTaxNumber(), citizenViewDTO.getSocialNumber());
                        log.warn(errorMessage);
                        return new InvalidCredentialsException(errorMessage);
                    }
            );
            var prescriptions = prescriptionDetailRepository.findAllByPatientId(patient.getId());

            var prescriptionInformationDTOS =
                    prescriptions.stream().map(
                            data -> {
                                String doctorFullName = constructDoctorFullName(data.getDoctorId());
                                return prescriptionMapper.toPrescriptionInformationDTO(data, doctorFullName);
                            }
                    ).collect(Collectors.toList());


            model.addAttribute("prescriptions", prescriptionInformationDTOS);
            model.addAttribute("patientName", "Nikos Koukos");
            return PATIENT_TEMPLATE_VALUE;
        } catch (Exception e) {
            model.addAttribute("error", "An error occurred: " + e.getMessage());
            return MAIN_TEMPLATE_VALUE;
        }
    }
//    	Geriatric	Skin Cancer Screening	03/10/2023

    private String constructDoctorFullName(Long doctorId) {
        var doctorAccount = doctorAccountRepository.findById(doctorId).orElseThrow(
                () -> {
                    var errorMessage = "An business error occurred, Reason: Doctor Account is not found, please contact with the support team.";
                    log.warn(errorMessage);
                    return new InvalidCredentialsException(errorMessage);
                }
        );

        return doctorAccount.getFirstName().concat(" ").concat(doctorAccount.getLastName().concat(" ,").concat(doctorAccount.getSpeciality()));
    }
}
