package org.example.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static org.example.utils.PaymentUtil.formatDate;
import static org.example.utils.PaymentUtil.generateRandomAmount;

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
        this.amount = generateRandomAmount();
        this.createdDate = formatDate(System.currentTimeMillis());
    }


}
