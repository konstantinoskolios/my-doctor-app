package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.entities.Payment;
import org.example.model.PaymentRequest;
import org.example.repositories.PaymentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;

    public List<Payment> retrievePayments(String doctorId) {
       return paymentRepository.findAllByDoctorId(doctorId);
    }

    public void addPayment(PaymentRequest paymentRequest) {
        paymentRepository.save(new Payment(paymentRequest.patientId(), paymentRequest.doctorId()));
    }
}
