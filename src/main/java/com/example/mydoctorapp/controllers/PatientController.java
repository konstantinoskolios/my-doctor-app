package com.example.mydoctorapp.controllers;

import com.example.mydoctorapp.dto.DoctorViewDto;
import com.example.mydoctorapp.services.DoctorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/citizens") //todo: lets review it after, patient/citizen
@RequiredArgsConstructor
@Slf4j
public class PatientController {

    private final DoctorService doctorService;

    @PostMapping("/add")
    public String addPatient(
            @RequestParam Long citizenId,
            @RequestParam Long doctorId,
            RedirectAttributes redirectAttributes) {
        doctorService.addPatient(citizenId, doctorId, redirectAttributes);
        return "redirect:/citizens/all"; // Adjust the URL as needed
    }

    @PostMapping("patient/addComment")
    public String addComment(@RequestBody DoctorViewDto doctorViewDto, RedirectAttributes redirectAttributes, Model model) {
        doctorService.addComment(doctorViewDto,redirectAttributes,model);
        return "doctor_view";
    }

    @PostMapping("patient/removePatient")
    public String removePatient(@RequestBody DoctorViewDto doctorViewDto, RedirectAttributes redirectAttributes, Model model) {
        doctorService.addComment(doctorViewDto,redirectAttributes,model);
        return "doctor_view";
    }
}
