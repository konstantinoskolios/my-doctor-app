package com.example.mydoctorapp.services;

import com.example.mydoctorapp.exceptions.InvalidCredentialsException;
import com.example.mydoctorapp.exceptions.InvalidEmailFormatException;
import com.example.mydoctorapp.repositories.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.regex.Pattern;

import static com.example.mydoctorapp.constants.Constants.DOCTOR_TEMPLATE_VALUE;
import static com.example.mydoctorapp.constants.Constants.REGEX_EMAIL_FORMAT;
import static com.example.mydoctorapp.utils.MyDoctorAppUtils.getCurrentTimeInGMT3;

@Service
@Slf4j
@RequiredArgsConstructor
public class DoctorService {

    private final AccountRepository accountRepository;

    public String loginDoctor(String email, String password, Model model) {
        log.info(String.format("Doctor with email: %s has successfully logged into the application at: %s", email, getCurrentTimeInGMT3()));
        model.addAttribute("hello123", "Hello world !!!!!!");
        return DOCTOR_TEMPLATE_VALUE;
    }

    private void isEmailExists(String email) {
        accountRepository.findAccountByEmail(email).orElseThrow(() -> {
            var errorMessage = String.format("No doctor account found with the provided email: %s", email);
            log.warn(String.format(errorMessage));
            throw new InvalidCredentialsException(errorMessage);
        });
    }

    private void isValidCredentials(String email, String password) {
        accountRepository.findAccountByEmailAndPass(email, password).orElseThrow(() -> {
            var errorMessage = String.format("Invalid login credentials for the doctor with email: %s", email);
            log.warn(errorMessage);
            throw new InvalidCredentialsException(errorMessage);
        });
    }

    private void isValidEmailFormat(String email) {
        var emailPattern = Pattern.compile(REGEX_EMAIL_FORMAT).matcher(email).matches();
        if (!emailPattern) throw new InvalidEmailFormatException(email);
    }
}