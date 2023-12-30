package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.entities.Payment;
import org.example.model.DoctorDetailsResponse;
import org.example.model.DoctorPaymentsDetailsResponse;
import org.example.model.DoctorPaymentsResponse;
import org.example.model.PatientDetailsResponse;
import org.example.model.PatientPaymentsResponse;
import org.example.model.PaymentRequest;
import org.example.model.PatientPaymentsDetailsResponse;
import org.example.repositories.PaymentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static org.example.utils.PaymentUtil.reduceDuplicatesIds;


@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final DoctorAppIntegrationService doctorAppIntegrationService;

    public DoctorPaymentsResponse retrieveDoctorPayments(String doctorId, String token) {
        var payments = paymentRepository.findAllByDoctorId(doctorId);
        var paymentsSum = payments.stream().mapToLong(Payment::getAmount).sum();
        var patientsId = reduceDuplicatesIds(payments.stream().map(Payment::getPatientId).toList());
        var patientDetails = doctorAppIntegrationService.retrievePatientsInformation(token, patientsId);
        return constructPaymentsResponse(payments, patientDetails, paymentsSum);
    }

    public PatientPaymentsResponse retrievePatientPayments(String patientId, String token) {
        var payments = paymentRepository.findAllByPatientId(patientId);
        var paymentsSum = payments.stream().mapToLong(Payment::getAmount).sum();
        var doctorsId = reduceDuplicatesIds(payments.stream().map(Payment::getDoctorId).toList());
        var doctorDetails = doctorAppIntegrationService.retrieveDoctorsInformation(token, doctorsId);
        return constructDoctorsResponse(payments, doctorDetails, paymentsSum);
    }

    public void addPayment(PaymentRequest paymentRequest) {
        paymentRepository.save(new Payment(paymentRequest.patientId(), paymentRequest.doctorId()));
    }

    private DoctorPaymentsResponse constructPaymentsResponse(List<Payment> payments, PatientDetailsResponse patientDetailsResponse, Long paymentsSum) {
        if (payments.isEmpty() || patientDetailsResponse.patientInformationMap().isEmpty())
            return new DoctorPaymentsResponse(null, 0L);

        var pdr = new ArrayList<PatientPaymentsDetailsResponse>();
        for (Payment payment : payments) {
            var patientInformation = patientDetailsResponse.patientInformationMap().get(payment.getPatientId());
            pdr.add(
                    new PatientPaymentsDetailsResponse(
                            patientInformation.fullName(),
                            patientInformation.taxNumber(),
                            patientInformation.socialSecurityNumber(),
                            patientInformation.phoneNumber(),
                            payment.getCreatedDate(),
                            String.valueOf(payment.getAmount())
                    ));
        }
        return new DoctorPaymentsResponse(pdr, paymentsSum);
    }

    private PatientPaymentsResponse constructDoctorsResponse(List<Payment> payments, DoctorDetailsResponse doctorDetailsResponse, Long paymentsSum) {
        if (payments.isEmpty() || doctorDetailsResponse.doctorInformationMap().isEmpty())
            return new PatientPaymentsResponse(null, 0L);

        var dpr = new ArrayList<DoctorPaymentsDetailsResponse>();
        for (Payment payment : payments) {
            var doctorInformation = doctorDetailsResponse.doctorInformationMap().get(payment.getDoctorId());
            dpr.add(
                    new DoctorPaymentsDetailsResponse(
                            doctorInformation.fullName(),
                            doctorInformation.email(),
                            doctorInformation.speciality(),
                            payment.getCreatedDate(),
                            String.valueOf(payment.getAmount())
                    ));
        }
        return new PatientPaymentsResponse(dpr, paymentsSum);
    }


}
