package org.example.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AppointmentStatus {
    PENDING("Pending"), REJECT("Rejected"), ACCEPT("Accepted");

    private final String value;

}
