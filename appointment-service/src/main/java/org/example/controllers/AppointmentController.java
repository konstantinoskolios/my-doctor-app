package org.example.controllers;

import lombok.RequiredArgsConstructor;
import org.example.model.AppointmentRequest;
import org.example.model.AvailableDatesResponse;
import org.example.model.DoctorAppointmentResponse;
import org.example.model.PatientAppointmentResponse;
import org.example.service.AppointmentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/appointment")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;

    @GetMapping("/doctor/retrieve")
    public DoctorAppointmentResponse retrieveDoctorAppointments(@RequestParam String doctorId, @RequestHeader("Authorization") String token) {
        return appointmentService.retrieveDoctorAppointments(doctorId, token);
    }

    @GetMapping("/patient/retrieve")
    public PatientAppointmentResponse retrievePatientAppointments(@RequestParam String patientId, @RequestHeader("Authorization") String token) {
        return appointmentService.retrievePatientAppointments(patientId, token);
    }

    /**
     * Lock the status of the appointment and ensure payment (optional)
     */
    @PostMapping("/status/{appointmentId}")
    public DoctorAppointmentResponse appointmentStatus(@PathVariable("appointmentId") String appointmentId, @RequestParam String isAccepted, @RequestHeader("Authorization") String token) {
        return appointmentService.changeAppointmentStatus(appointmentId, isAccepted, token);
    }

    @GetMapping("/dates")
    public AvailableDatesResponse retrieveAvailableDates(@RequestParam String doctorId, @RequestHeader("Authorization") String token){
        return appointmentService.retrieveAvailableDates(doctorId);
    }

    @PostMapping("/add")
    public String addAppointment(@RequestBody AppointmentRequest appointmentRequest){
        return appointmentService.addAppointment(appointmentRequest);
    }



}
