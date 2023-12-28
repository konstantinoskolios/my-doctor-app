package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.entities.Payment;
import org.example.model.PatientDetailsResponse;
import org.example.model.PaymentRequest;
import org.example.model.PaymentsDetailsResponse;
import org.example.model.PaymentsResponse;
import org.example.repositories.PaymentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final DoctorAppIntegrationService doctorAppIntegrationService;

    public PaymentsResponse retrievePayments(String doctorId, String token) {
        var payments = paymentRepository.findAllByDoctorId(doctorId);
        var paymentsSum = payments.stream().mapToLong(Payment::getAmount).sum();
        var patientsId = payments.stream().map(Payment::getPatientId).toList();
        var patientDetails = doctorAppIntegrationService.retrievePatientsInformation(token, patientsId);
        return constructPaymentsResponse(payments, patientDetails, paymentsSum);
    }

    public void addPayment(PaymentRequest paymentRequest) {
        paymentRepository.save(new Payment(paymentRequest.patientId(), paymentRequest.doctorId()));
    }

    private PaymentsResponse constructPaymentsResponse(List<Payment> payments, PatientDetailsResponse patientDetailsResponse, Long paymentsSum) {
        if (payments.isEmpty() || patientDetailsResponse.patientInformationMap().isEmpty())
            return new PaymentsResponse(null, 0L);

        var pdr = new ArrayList<PaymentsDetailsResponse>();
        for (Payment payment : payments) {
            var patientInformation = patientDetailsResponse.patientInformationMap().get(payment.getPatientId());
            pdr.add(
                    new PaymentsDetailsResponse(
                            patientInformation.fullName(),
                            patientInformation.taxNumber(),
                            patientInformation.socialSecurityNumber(),
                            patientInformation.phoneNumber(),
                            payment.getCreatedDate(),
                            String.valueOf(payment.getAmount())
                    ));
        }
        return new PaymentsResponse(pdr, paymentsSum);
    }


}
