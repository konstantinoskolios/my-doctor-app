package com.example.mydoctorapp.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.swing.plaf.SeparatorUI;

@EqualsAndHashCode(callSuper = true)
@Data
public class GuiException extends RuntimeException {
    public GuiException() {
        super("An error occurred by the ui side. Please try again or contact the support team.");
    }

    public GuiException(String message){
        super(message);
    }
}
