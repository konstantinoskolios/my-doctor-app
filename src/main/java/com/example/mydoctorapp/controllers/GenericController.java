package com.example.mydoctorapp.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class GenericController {

    @GetMapping("/")
    public String userAdminPage(Model model) {
        return "prescriptions_view";
    }


}
