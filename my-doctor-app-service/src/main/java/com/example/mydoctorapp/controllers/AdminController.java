package com.example.mydoctorapp.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.example.mydoctorapp.constants.Constants.ADMIN_VIEW;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/view")
    public String doctorView(Model model, @AuthenticationPrincipal OidcUser user) {
        model.addAttribute("fullName",user.getFullName());
        return ADMIN_VIEW;
    }
}
