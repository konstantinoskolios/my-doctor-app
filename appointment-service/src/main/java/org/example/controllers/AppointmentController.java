package org.example.controllers;

import lombok.RequiredArgsConstructor;
import org.example.model.DoctorAppointmentResponse;
import org.example.service.AppointmentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/appointment")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;

    @GetMapping("/retrieve")
    public DoctorAppointmentResponse retrieveDoctorAppointments(@RequestParam String doctorId, @RequestHeader("Authorization") String token) {
        return appointmentService.retrieveDoctorAppointments(doctorId, token);
    }

    /**
     * Lock the status of the appointment and ensure payment (optional)
     */
    @PostMapping("/status/{appointmentId}")
    public DoctorAppointmentResponse appointmentStatus(@PathVariable("appointmentId") String appointmentId, @RequestParam String isAccepted, @RequestHeader("Authorization") String token) {
        return appointmentService.changeAppointmentStatus(appointmentId, isAccepted, token);
    }

}
