package com.example.mydoctorapp.exceptions;

import com.example.mydoctorapp.exceptions.ui.ErrorResponseModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import javax.xml.bind.ValidationException;
import java.util.ArrayList;

@ControllerAdvice
@Slf4j
public class ExceptionAdvisor {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        String errorMessage = "An error occurred: " + e.getMessage();
        log.error(errorMessage, e);
        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<String> handleValidationException(ValidationException e) {
        String errorMessage = "Validation failed: " + e.getMessage();
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<String> handleNoHandlerFoundException(NoHandlerFoundException e) {
        String errorMessage = "Page not found: " + e.getRequestURL();
        log.error(errorMessage, e);
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<String> handleInvalidCredentialsException(InvalidCredentialsException e) {
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(GuiException.class)
    public ResponseEntity<String> handelGuiException(GuiException e) {
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseModel> handleValidationException(MethodArgumentNotValidException ex) {
        var bindingResult = ex.getBindingResult();
        var errorMessages = new ArrayList<String>();

        bindingResult.getFieldErrors().forEach(fieldError -> {
            String errorMessage = "Field '" + fieldError.getField() + "': " + fieldError.getDefaultMessage();
            errorMessages.add(errorMessage);
        });
        log.error(String.valueOf(errorMessages), ex);
        return new ResponseEntity<>(new ErrorResponseModel(errorMessages), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public String handleNoResourceFoundException(NoResourceFoundException ex) {
        return "Index"; //avoid this for production for development is a nice way
    }
}
