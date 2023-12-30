package org.example.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.entities.Appointment;
import org.example.model.AppointmentRequest;
import org.example.model.AvailableDatesResponse;
import org.example.model.DoctorAppointment;
import org.example.model.DoctorAppointmentResponse;
import org.example.model.DoctorDetailsResponse;
import org.example.model.PatientAppointment;
import org.example.model.PatientAppointmentResponse;
import org.example.model.PatientDetailsResponse;
import org.example.model.PaymentRequest;
import org.example.repositories.AppointmentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static org.example.utility.CalendarUtility.generateWorkingDates;


@Service
@RequiredArgsConstructor
@Slf4j
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final DoctorAppIntegrationService doctorAppIntegrationService;
    private final PaymentIntegrationService paymentIntegrationService;


    /**
     * better using models for such cases, but it's acceptable for the purpose of this project
     */
    public DoctorAppointmentResponse retrieveDoctorAppointments(String doctorId, String token) {
        var doctorAppointments = appointmentRepository.findAllByDoctorId(doctorId);
        var patientsId = doctorAppointments.stream().map(Appointment::getPatientId).toList();
        var patientDetails = doctorAppIntegrationService.retrievePatientsInformation(token, patientsId);
        return constructDoctorAppointmentResponse(doctorAppointments, patientDetails);
    }

    public PatientAppointmentResponse retrievePatientAppointments(String patientId, String token) {
        var patientAppointments = appointmentRepository.findAllByPatientId(patientId);
        var doctorIds = patientAppointments.stream().map(Appointment::getDoctorId).toList();
        var doctorDetails = doctorAppIntegrationService.retrieveDoctorsInformation(token, doctorIds);
        return constructPatientAppointmentResponse(patientAppointments, doctorDetails);
    }

    /**
     * controller advisor will handle the error, not try catch is needed for this scenario
     */
    @Transactional
    public DoctorAppointmentResponse changeAppointmentStatus(String appointmentId, String isAccepted, String token) {

        var appointment = appointmentRepository.findById(Long.valueOf(appointmentId)).orElseThrow(() -> new IllegalArgumentException("The appointment is not exists or valid, please contact with admins"));

        if (Objects.equals(isAccepted, "Accept")) {
            addPayment(appointmentId, token);
        }
        appointment.setStatus(isAccepted);
        appointmentRepository.save(appointment);
        return retrieveDoctorAppointments(appointment.getDoctorId(), token);
    }

    private void addPayment(String appointmentId, String token) {
        var appointment = appointmentRepository.findById(Long.valueOf(appointmentId)).orElseThrow(() -> new IllegalArgumentException("An error occurred with records, contact admins"));
        paymentIntegrationService.addPayment(token, new PaymentRequest(appointment.getPatientId(), appointment.getDoctorId()));
    }

    private DoctorAppointmentResponse constructDoctorAppointmentResponse(List<Appointment> appointments, PatientDetailsResponse patientDetailsResponse) {
        if (appointments.isEmpty() || patientDetailsResponse.patientInformationMap().isEmpty())
            return new DoctorAppointmentResponse(null);

        var doctorAppointments = new ArrayList<DoctorAppointment>();
        var patientInfo = patientDetailsResponse.patientInformationMap();
        for (Appointment appointment : appointments) {
            doctorAppointments.add(
                    new DoctorAppointment(appointment.getId(),
                            patientInfo.get(appointment.getPatientId()).fullName(),
                            patientInfo.get(appointment.getPatientId()).socialSecurityNumber(),
                            patientInfo.get(appointment.getPatientId()).phoneNumber(),
                            appointment.getAppointmentDate(),
                            appointment.getStatus()
                    ));
        }
        return new DoctorAppointmentResponse(doctorAppointments);
    }

    private PatientAppointmentResponse constructPatientAppointmentResponse(List<Appointment> appointments, DoctorDetailsResponse doctorDetailsResponse) {
        if (appointments.isEmpty() || doctorDetailsResponse.doctorInformationMap().isEmpty())
            return new PatientAppointmentResponse(null);

        var patientAppointments = new ArrayList<PatientAppointment>();
        var doctorInfo = doctorDetailsResponse.doctorInformationMap();
        for (Appointment appointment : appointments) {
            patientAppointments.add(
                    new PatientAppointment(appointment.getId(),
                            doctorInfo.get(appointment.getDoctorId()).fullName(),
                            doctorInfo.get(appointment.getDoctorId()).email(),
                            doctorInfo.get(appointment.getDoctorId()).speciality(),
                            appointment.getAppointmentDate(),
                            appointment.getStatus()
                    ));
        }
        return new PatientAppointmentResponse(patientAppointments);
    }

    private Map<String, List<String>> availableDaysPerDoctor = new HashMap<>();
    private static final String START_DATE = "01-01-2023 09:00";
    private static final String END_DATE = "31-12-2023 15:00";

    public AvailableDatesResponse retrieveAvailableDates(String doctorId) {
        var availableDates = availableDaysPerDoctor.get(doctorId);

        if (availableDates != null) {
            return new AvailableDatesResponse(availableDates);
        }

        var generatedDates = generateWorkingDates(START_DATE, END_DATE);
        availableDaysPerDoctor.put(doctorId, new ArrayList<>(generatedDates));
        return new AvailableDatesResponse(generatedDates);

    }

    public String addAppointment(AppointmentRequest appointmentRequest) {
        try {
            var doctorDates = availableDaysPerDoctor.get(appointmentRequest.doctorId());
            if (doctorDates != null && doctorDates.remove(appointmentRequest.selectedDate())) {
                appointmentRepository.save(new Appointment(appointmentRequest.patientId(), appointmentRequest.doctorId(), appointmentRequest.selectedDate()));
                return String.format("Appointment for %s added successfully!", appointmentRequest.selectedDate());
            }
            return String.format("Appointment for %s not available.", appointmentRequest.selectedDate());

        } catch (Exception e) {
            return "Error adding appointment: " + e.getMessage();
        }
    }
}
