package com.example.mydoctorapp.services;

import com.example.mydoctorapp.dto.CitizenViewDTO;
import com.example.mydoctorapp.entities.Citizen;
import com.example.mydoctorapp.entities.PatientAccount;
import com.example.mydoctorapp.exceptions.InvalidCredentialsException;
import com.example.mydoctorapp.repositories.CitizenRepository;
import com.example.mydoctorapp.repositories.PatientAccountRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import static com.example.mydoctorapp.constants.Constants.CITIZENS;
import static com.example.mydoctorapp.constants.Constants.CITIZENS_TEMPLATE_VALUE;
import static com.example.mydoctorapp.constants.Constants.END_ITEM;
import static com.example.mydoctorapp.constants.Constants.ONE_VALUE;
import static com.example.mydoctorapp.constants.Constants.SORT_ASCENDING;
import static com.example.mydoctorapp.constants.Constants.START_ITEM;
import static com.example.mydoctorapp.constants.Constants.USER_REGISTER_VIEW;
import static com.example.mydoctorapp.specifications.CitizenSpecification.constructCitizenSpecification;

@Service
@Slf4j
@RequiredArgsConstructor
public class CitizenService {
    private final CitizenRepository citizenRepository;
    private final PatientAccountRepository patientAccountRepository;

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

    @Transactional
    public String registerCitizen(CitizenViewDTO citizenViewDTO, Model model, OidcUser user) {

        var fullName = user.getUserInfo().getFullName();
        String[] parts = fullName.split(" ");
        var firstName = parts[1];
        var lastName = parts[2];
        model.addAttribute("firstName",firstName);
        model.addAttribute("lastName",lastName);

        try {
            var patient = citizenRepository.findByTaxNumberAndSocialSecurityNumber(citizenViewDTO.getTaxNumber(), citizenViewDTO.getSocialNumber()).orElseThrow(
                    () -> {
                        var errorMessage = String.format("Invalid login credentials for the citizen with tax number: %s and social security number: %s", citizenViewDTO.getTaxNumber(), citizenViewDTO.getSocialNumber());
                        log.warn(errorMessage);
                        return new InvalidCredentialsException(errorMessage);
                    }
            );

            patient.setFirstName(firstName);
            patient.setLastName(lastName);
            patient.setRegisterId(user.getUserInfo().getSubject());

            patientAccountRepository.save(new PatientAccount(
                    patient.getRegisterId(),
                    patient.getFirstName(),
                    patient.getLastName(),
                    patient.getFatherFirstName(),
                    patient.getTaxNumber(),
                    patient.getSocialSecurityNumber(),
                    patient.getPhoneNumber(),
                    patient.getBirthdate(),
                    null,null,null));

            citizenRepository.save(patient);

            return "redirect:/user/view";
        } catch (Exception e) {
            model.addAttribute("error", "An error occurred: " + e.getMessage());
            model.addAttribute("firstName", firstName);
            model.addAttribute("lastName",lastName);
            return USER_REGISTER_VIEW;
        }
    }

}
