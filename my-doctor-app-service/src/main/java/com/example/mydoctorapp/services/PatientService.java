package com.example.mydoctorapp.services;

import com.example.mydoctorapp.repositories.CitizenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import static com.example.mydoctorapp.constants.Constants.USER_REGISTER_VIEW;
import static com.example.mydoctorapp.constants.Constants.YES_VALUE;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final CitizenRepository citizenRepository;

    public String isUserRegister(OidcUser user, Model model){
        var userId = citizenRepository.findByRegisterId(user.getUserInfo().getSubject());

        if(userId.isPresent()){
            model.addAttribute("patientId",userId.get().getRegisterId());
            return YES_VALUE;
        }

        return USER_REGISTER_VIEW;

    }
}
