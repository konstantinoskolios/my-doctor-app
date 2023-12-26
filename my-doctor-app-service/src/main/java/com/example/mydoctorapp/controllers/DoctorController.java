package com.example.mydoctorapp.controllers;

import com.example.mydoctorapp.services.DoctorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@Slf4j
public class DoctorController {
    private final DoctorService doctorService;

//    @PostMapping("/login")
//    public String loginDoctor(
//            @RequestParam String email,
//            @RequestParam String password,
//            Model model) {
//        return doctorService.loginDoctor(email, password, model);
//    }

}
