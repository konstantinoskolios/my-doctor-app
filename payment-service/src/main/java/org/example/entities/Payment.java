package org.example.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.concurrent.ThreadLocalRandom;

import static org.example.utils.PaymentUtil.formatDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String patientId;
    private String doctorId;
    private String createdDate;
    private Long amount;

    public Payment(String patientId, String doctorId) {
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.amount = ThreadLocalRandom.current().nextLong(10,100);
        this.createdDate = formatDate(System.currentTimeMillis());
    }


}
