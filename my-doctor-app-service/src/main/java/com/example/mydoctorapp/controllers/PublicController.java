package com.example.mydoctorapp.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import static com.example.mydoctorapp.constants.Constants.ABOUT_VIEW;
import static com.example.mydoctorapp.constants.Constants.INDEX_VIEW;
import static com.example.mydoctorapp.constants.Constants.LOGIN_VIEW;

@Controller
@RequiredArgsConstructor
@Slf4j
public class PublicController {

    @GetMapping("/")
    public String userAdminPage() {
        return INDEX_VIEW;
    }

    @GetMapping("/login")
    public String login() {
        return LOGIN_VIEW;
    }

    @GetMapping("/about")
    public String about() {
        return ABOUT_VIEW;
    }


}
