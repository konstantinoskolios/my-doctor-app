package org.example.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.entities.Appointment;
import org.example.model.DoctorAppointment;
import org.example.model.DoctorAppointmentResponse;
import org.example.model.PatientDetailsResponse;
import org.example.model.PaymentRequest;
import org.example.repositories.AppointmentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


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

    /**
     * controller advisor will handle the error, not try catch is needed for this scenario
     */
    @Transactional
    public DoctorAppointmentResponse changeAppointmentStatus(String appointmentId, String isAccepted, String token) {

        var appointment = appointmentRepository.findById(Long.valueOf(appointmentId)).orElseThrow(() -> new IllegalArgumentException("The appointment is not exists or valid, please contact with admins"));

        if (Objects.equals(isAccepted, "Accept")) {
            appointment.setStatus(isAccepted);
            appointmentRepository.save(appointment);
            addPayment(appointmentId, token);

        }
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
}
