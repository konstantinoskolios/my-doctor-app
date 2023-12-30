package com.example.mydoctorapp.controllers;

import com.example.mydoctorapp.dto.DoctorViewDTO;
import com.example.mydoctorapp.services.DoctorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.example.mydoctorapp.constants.Constants.PRESCRIPTIONS_TEMPLATE_VALUE;
import static com.example.mydoctorapp.constants.Constants.USER_VIEW;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/user/view")
public class UserController {

    private final DoctorService doctorService;

    @GetMapping("prescriptions")
    public String getPrescriptions(DoctorViewDTO doctorViewDto, Model model) {
        doctorService.getPrescriptions(doctorViewDto, model);
        return PRESCRIPTIONS_TEMPLATE_VALUE;
    }

    @GetMapping("/details")
    public String getDetails(Model model, @AuthenticationPrincipal OidcUser user) {
        doctorService.getAllDoctors(model);
        return USER_VIEW;
    }
}
