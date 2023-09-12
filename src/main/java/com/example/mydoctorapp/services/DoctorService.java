package com.example.mydoctorapp.services;

import com.example.mydoctorapp.dto.DoctorViewDto;
import com.example.mydoctorapp.entities.DoctorAccount;
import com.example.mydoctorapp.exceptions.GuiException;
import com.example.mydoctorapp.exceptions.InvalidCredentialsException;
import com.example.mydoctorapp.exceptions.InvalidEmailFormatException;
import com.example.mydoctorapp.mapstruct.CitizenMapper;
import com.example.mydoctorapp.mapstruct.DoctorMapper;
import com.example.mydoctorapp.repositories.CitizenRepository;
import com.example.mydoctorapp.repositories.DoctorAccountRepository;
import com.example.mydoctorapp.repositories.PatientAccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.transaction.Transactional;
import java.util.regex.Pattern;

import static com.example.mydoctorapp.constants.Constants.DOCTOR_TEMPLATE_VALUE;
import static com.example.mydoctorapp.constants.Constants.FAILURE_MESSAGE_ALERT;
import static com.example.mydoctorapp.constants.Constants.GENERIC_ERROR_FOR_UI;
import static com.example.mydoctorapp.constants.Constants.MAIN_TEMPLATE_VALUE;
import static com.example.mydoctorapp.constants.Constants.REGEX_EMAIL_FORMAT;
import static com.example.mydoctorapp.constants.Constants.SUCCESS_MESSAGE_ALERT;
import static com.example.mydoctorapp.utils.MyDoctorAppUtils.getCurrentTimeInGMT3;

@Service
@Slf4j
@RequiredArgsConstructor
public class DoctorService {

    private final DoctorAccountRepository doctorAccountRepository;
    //    private final DoctorPatientRelationRepository doctorPatientRelationRepository;
    private final PatientAccountRepository patientAccountRepository;
    private final CitizenRepository citizenRepository;
    private final DoctorMapper doctorMapper;
    private final CitizenMapper citizenMapper;

    public String loginDoctor(String email, String password, Model model) {
       try{
            isValidEmailFormat(email);
            isEmailExists(email);
            var doctorAccount = retrieveDoctorAccount(email, password);
            log.info(String.format("Doctor with email: %s has successfully logged into the application at: %s", email, getCurrentTimeInGMT3()));
            model.addAttribute("doctorAccount", doctorMapper.toDto(doctorAccount));
            var patientList = patientAccountRepository.findAllByDoctorId(doctorAccount.getId());
            model.addAttribute("patientList", patientList);
           return DOCTOR_TEMPLATE_VALUE;
        }catch(Exception e) {
           model.addAttribute("error", "An error occurred: " + e.getMessage());
           return MAIN_TEMPLATE_VALUE;
       }
    }

    private void isEmailExists(String email) {
        doctorAccountRepository.findDoctorAccountByEmail(email).orElseThrow(() -> {
            var errorMessage = String.format("No doctor account found with the provided email: %s", email);
            log.warn(String.format(errorMessage));
            throw new InvalidCredentialsException(errorMessage);
        });
    }

    private DoctorAccount retrieveDoctorAccount(String email, String password) {
        return doctorAccountRepository.findDoctorAccountByEmailAndPass(email, password).orElseThrow(() -> {
            var errorMessage = String.format("Invalid login credentials for the doctor with email: %s", email);
            log.warn(errorMessage);
            throw new InvalidCredentialsException(errorMessage);
        });
    }

    private void isValidEmailFormat(String email) {
        var emailPattern = Pattern.compile(REGEX_EMAIL_FORMAT).matcher(email).matches();
        if (!emailPattern) throw new InvalidEmailFormatException(email);
    }

    public void addPatient(Long citizenId, Long doctorId, RedirectAttributes redirectAttributes) {

        try {
            log.info("Citizen id: {} doctorId : {}", citizenId, doctorId);
            var citizenInfo = citizenRepository.findById(citizenId).orElseThrow(GuiException::new);
            var patient = citizenMapper.citizenToPatientAccount(citizenInfo, doctorId);
            if (patientAccountRepository.existsById(patient.getId())) throw new GuiException("Patient has already been added, if not showing refresh the doctor page");
            patientAccountRepository.save(patient);
            redirectAttributes.addFlashAttribute(SUCCESS_MESSAGE_ALERT, "Patient has been added successfully");
        } catch (Exception e) {
            log.error("An error occurred during the addition of the patient: {} and exception: {}", e.getMessage(), e);
            redirectAttributes.addFlashAttribute(FAILURE_MESSAGE_ALERT, e.getMessage());
        }
    }

    public void addComment(DoctorViewDto doctorViewDto, RedirectAttributes redirectAttributes, Model model) {
        try {
            var patientAccount = patientAccountRepository.findByIdAndDoctorId(doctorViewDto.getPatientId(), doctorViewDto.getDoctorId()).orElseThrow(GuiException::new);
            patientAccount.setComments(doctorViewDto.getComment());
            patientAccountRepository.save(patientAccount);
            redirectAttributes.addFlashAttribute(SUCCESS_MESSAGE_ALERT, "Comment has been changed successfully");
            constructDoctorTabAttributes(doctorViewDto.getDoctorId(), model);
        } catch (Exception e) {
            log.error("An error occurred during edit of the comment of the patient: {} and exception: {}", e.getMessage(), e);
            redirectAttributes.addFlashAttribute(FAILURE_MESSAGE_ALERT, GENERIC_ERROR_FOR_UI);
        }
    }

    /**
     * In case an error occured we want to ensure that the record is immutable
     */
    @Transactional
    public void removePatient(DoctorViewDto doctorViewDto, RedirectAttributes redirectAttributes, Model model) {
        try {
            patientAccountRepository.deleteById(doctorViewDto.getPatientId());
            constructDoctorTabAttributes(doctorViewDto.getDoctorId(), model);
        } catch (Exception e) {
            log.error("An error occurred removing the patient: {} with exception: {}", e.getMessage(), e);
            redirectAttributes.addFlashAttribute(FAILURE_MESSAGE_ALERT, GENERIC_ERROR_FOR_UI);
        }
    }

    private void constructDoctorTabAttributes(Long doctorId, Model model) {
        var doctorAccount = doctorAccountRepository.findById(doctorId).orElseThrow(GuiException::new);
        var patientList = patientAccountRepository.findAllByDoctorId(doctorId);
        model.addAttribute("doctorAccount", doctorMapper.toDto(doctorAccount));
        model.addAttribute("patientList", patientList);
    }
}
