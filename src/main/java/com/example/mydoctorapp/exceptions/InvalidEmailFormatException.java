package com.example.mydoctorapp.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class InvalidEmailFormatException extends RuntimeException {
    private static final String ERROR_MESSAGE = "Email format is invalid: %s. Please use the format 'youremail@mydoctorapp.com'. If you do not have a registered email, kindly contact our support team to facilitate the creation of your credentials. Please note that only verified doctors are eligible to create email addresses.";
    public InvalidEmailFormatException(String email) {
        super(String.format(ERROR_MESSAGE, email));
    }
}
