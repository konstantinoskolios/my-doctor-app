package com.example.mydoctorapp.controllers;

import com.example.mydoctorapp.dto.DoctorViewDTO;
import com.example.mydoctorapp.model.AppointmentRequest;
import com.example.mydoctorapp.services.DoctorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import static com.example.mydoctorapp.constants.Constants.PRESCRIPTIONS_TEMPLATE_VALUE;

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
    public String getDetails(Model model,@AuthenticationPrincipal OidcUser user) {
        doctorService.getAllDoctors(model);
        return "user/view";
    }

//    @GetMapping("/getAvailableDates")
//    @ResponseBody
//    public List<String> getAvailableDates(@RequestParam String doctorId) {
//        return doctorService.getAvailableDates(doctorId);
//    }
//
//    @PostMapping("/scheduleAppointment")
//    @ResponseBody
//    public String scheduleAppointment(@RequestBody AppointmentRequest appointmentRequest) {
//        return doctorService.scheduleAppointment(appointmentRequest);
//    }
}
