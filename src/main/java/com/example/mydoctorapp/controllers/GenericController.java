package com.example.mydoctorapp.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@Slf4j
public class GenericController {

    @GetMapping("/")
    public String userAdminPage(Model model) {
        return "main";
    }

    @PostMapping("/login")
    public String loginDoctor(@RequestParam String email, @RequestParam String password, Model model) {
        //todo: Add logic of very simple logic, should i use spring - security better;
        return "main";
    }


}
