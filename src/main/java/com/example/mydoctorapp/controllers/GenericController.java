package com.example.mydoctorapp.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import static com.example.mydoctorapp.constants.Constants.MAIN_TEMPLATE_VALUE;

@Controller
@RequiredArgsConstructor
@Slf4j
public class GenericController {

    @GetMapping("/")
    public String userAdminPage(Model model) {
        return MAIN_TEMPLATE_VALUE;
    }


}
