package com.example.mydoctorapp.controllers;

import com.example.mydoctorapp.services.DoctorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/super-user")
public class SuperUserController {

    private final DoctorService doctorService;
    @GetMapping("/view")
    public String doctorView(Model model, @AuthenticationPrincipal OidcUser user){
         return doctorService.loginSuperUser(model,user);
    }
}
