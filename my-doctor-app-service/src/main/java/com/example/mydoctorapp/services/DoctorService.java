package com.example.mydoctorapp.services;

import com.example.mydoctorapp.dto.AttachPrescriptionDTO;
import com.example.mydoctorapp.dto.DoctorViewDTO;
import com.example.mydoctorapp.dto.PrescriptionInformationDTO;
import com.example.mydoctorapp.entities.DoctorAccount;
import com.example.mydoctorapp.entities.DoctorPatientRelationship;
import com.example.mydoctorapp.entities.PatientAccount;
import com.example.mydoctorapp.entities.PrescriptionDetail;
import com.example.mydoctorapp.enumerations.PrescriptionCategoryEnum;
import com.example.mydoctorapp.exceptions.GuiException;
import com.example.mydoctorapp.exceptions.InvalidEmailFormatException;
import com.example.mydoctorapp.mapstruct.CitizenMapper;
import com.example.mydoctorapp.mapstruct.DoctorMapper;
import com.example.mydoctorapp.repositories.CitizenRepository;
import com.example.mydoctorapp.repositories.DoctorAccountRepository;
import com.example.mydoctorapp.repositories.DoctorPatientRelationshipRepository;
import com.example.mydoctorapp.repositories.PatientAccountRepository;
import com.example.mydoctorapp.repositories.PrescriptionDetailRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static com.example.mydoctorapp.constants.Constants.DOCTOR_SPECIALITY;
import static com.example.mydoctorapp.constants.Constants.ERROR_ATTRIBUTE;
import static com.example.mydoctorapp.constants.Constants.FAILURE_MESSAGE_ALERT;
import static com.example.mydoctorapp.constants.Constants.GENERIC_ERROR_FOR_UI;
import static com.example.mydoctorapp.constants.Constants.INDEX_VIEW;
import static com.example.mydoctorapp.constants.Constants.REGEX_EMAIL_FORMAT;
import static com.example.mydoctorapp.constants.Constants.SUCCESS_MESSAGE_ALERT;
import static com.example.mydoctorapp.constants.Constants.SUPER_USER_VIEW;
import static com.example.mydoctorapp.entities.DoctorAccount_.email;
import static com.example.mydoctorapp.utils.MyDoctorAppUtils.getCurrentTimeInGMT3;

@Service
@Slf4j
@RequiredArgsConstructor
public class DoctorService {
    private final DoctorAccountRepository doctorAccountRepository;
    private final PatientAccountRepository patientAccountRepository;
    private final CitizenRepository citizenRepository;
    private final PrescriptionDetailRepository prescriptionDetailRepository;
    private final DoctorMapper doctorMapper;
    private final CitizenMapper citizenMapper;
    private final DoctorPatientRelationshipRepository doctorPatientRelationshipRepository;

    //    @Transactional #problem with redirect
    public String loginSuperUser(Model model, OidcUser user) {
        try {
            isValidEmailFormat(user.getEmail(), user.getEmailVerified());
            var doctorAccount = updateSuperUserInfo(user);
            log.info(String.format("Doctor with email: %s has successfully logged into the application at: %s", email, getCurrentTimeInGMT3()));
            model.addAttribute("doctorAccount", doctorAccount);
            model.addAttribute("patientList", retrievePatientAccounts(doctorAccount.getId()));
            return SUPER_USER_VIEW;

        } catch (GuiException e) {
            model.addAttribute(ERROR_ATTRIBUTE, e.getMessage());
            return INDEX_VIEW;
        } catch (InvalidDataAccessResourceUsageException e) {
            model.addAttribute(ERROR_ATTRIBUTE, "Something is wrong with schema or database, contact your administrators.");
            return INDEX_VIEW;
        } catch (Exception e) {
            model.addAttribute(ERROR_ATTRIBUTE, "An error occurred: " + e.getLocalizedMessage());
            return INDEX_VIEW;
        }
    }

    private List<PatientAccount> retrievePatientAccounts(String doctorId) {
        return doctorPatientRelationshipRepository.findByDoctorId(doctorId).stream().map(DoctorPatientRelationship::getPatient).toList();
    }

    private DoctorAccount updateSuperUserInfo(OidcUser user) {
        var fullName = user.getFullName();
        var email = user.getEmail();
        var subId = user.getSubject();
        var speciality = "";
        try {
            speciality = user.getClaims().get(DOCTOR_SPECIALITY).toString();
        } catch (Exception e) {
            throw new GuiException("Please contact with administrator to add you a speciality.");
        }
        var doctorAccount = new DoctorAccount(subId, email, fullName, speciality);
        if (doctorAccountRepository.existsById(user.getUserInfo().getSubject())) return doctorAccount;
        return doctorAccountRepository.save(doctorAccount);
    }

    private void isValidEmailFormat(String email, boolean verifiedEmail) {
        var emailPattern = Pattern.compile(REGEX_EMAIL_FORMAT).matcher(email).matches();
        if (!emailPattern || !verifiedEmail) throw new InvalidEmailFormatException(email);
    }

    public void addPatient(String citizenId, String doctorId, RedirectAttributes redirectAttributes) {

        try {
            log.info("Citizen id: {} doctorId : {}", citizenId, doctorId);
            var citizenInfo = citizenRepository.findById(citizenId).orElseThrow(GuiException::new);
            var patient = citizenMapper.citizenToPatientAccount(citizenInfo, doctorId);
            if (patientAccountRepository.existsByIdAndDoctorRelationships_DoctorId(patient.getId(), doctorId))
                throw new GuiException("Patient has already been added, if not showing refresh the doctor page");

            DoctorPatientRelationship relationship = new DoctorPatientRelationship();
            relationship.setDoctor(doctorAccountRepository.findById(doctorId).orElseThrow(() -> new GuiException("Doctor not found")));
            relationship.setPatient(patient);
            patient.getDoctorRelationships().add(relationship);
            patientAccountRepository.save(patient);
            redirectAttributes.addFlashAttribute(SUCCESS_MESSAGE_ALERT, "Patient has been added successfully");
        } catch (Exception e) {
            log.error("An error occurred during the addition of the patient: {} and exception: {}", e.getMessage(), e);
            redirectAttributes.addFlashAttribute(FAILURE_MESSAGE_ALERT, e.getMessage());
        }
    }

    public void addComment(DoctorViewDTO doctorViewDto, Model model) {
        try {
            var patientAccount = patientAccountRepository.findByIdAndDoctorRelationships_DoctorId(doctorViewDto.getPatientId(), doctorViewDto.getDoctorId()).orElseThrow(GuiException::new);
            patientAccount.setComments(doctorViewDto.getComment());
            patientAccountRepository.save(patientAccount);
            model.addAttribute(SUCCESS_MESSAGE_ALERT, "Comment has been changed successfully");
            constructDoctorTabAttributes(doctorViewDto.getDoctorId(), model);
        } catch (Exception e) {
            log.error("An error occurred during edit of the comment of the patient: {} and exception: {}", e.getMessage(), e);
            model.addAttribute(FAILURE_MESSAGE_ALERT, GENERIC_ERROR_FOR_UI);
        }
    }

    /**
     * In case an error occurred we want to ensure that the record is immutable
     */
    @Transactional
    public void removePatient(DoctorViewDTO doctorViewDto, RedirectAttributes redirectAttributes, Model model) {
        try {
            doctorPatientRelationshipRepository.deleteByDoctorIdAndPatientId(doctorViewDto.getDoctorId(), doctorViewDto.getPatientId());
            constructDoctorTabAttributes(doctorViewDto.getDoctorId(), model);
        } catch (Exception e) {
            log.error("An error occurred removing the patient: {} with exception: {}", e.getMessage(), e);
            redirectAttributes.addFlashAttribute(FAILURE_MESSAGE_ALERT, GENERIC_ERROR_FOR_UI);
        }
    }

    private void constructDoctorTabAttributes(String doctorId, Model model) {
        var doctorAccount = doctorAccountRepository.findById(doctorId).orElseThrow(GuiException::new);
        var patientList = retrievePatientAccounts(doctorId);
        model.addAttribute("doctorAccount", doctorMapper.toDto(doctorAccount));
        model.addAttribute("patientList", patientList);
    }


    @Transactional
    public void attachPrescriptions(AttachPrescriptionDTO input) {
        var patientAccount = patientAccountRepository.findByIdAndDoctorRelationships_DoctorId(input.getPatientId(), input.getDoctorId()).orElseThrow(() -> new GuiException("Patient not Found"));


        var prescriptionDetails = input
                .getPrescriptionsInformation()
                .stream()
                .map(prescription -> constructPrescriptionDetail(patientAccount, prescription, input.getDoctorId())).collect(Collectors.toList());
        prescriptionDetailRepository.deleteByPatientIdAndDoctorId(input.getPatientId(), input.getDoctorId());
        prescriptionDetailRepository.saveAll(prescriptionDetails);
    }

    private PrescriptionDetail constructPrescriptionDetail(PatientAccount patientAccount, PrescriptionInformationDTO prescription, String doctorId) {
        return PrescriptionDetail.builder()
                .patientId(patientAccount.getId())
                .doctorId(doctorId)
                .category(prescription.getCategory())
                .prescription(prescription.getPrescriptionName())
                .date(prescription.getDate())
                .build();
    }

    public void getPrescriptions(DoctorViewDTO doctorViewDto, Model model) {

        var prescriptionsDetails = prescriptionDetailRepository.findAllByPatientIdAndDoctorId(doctorViewDto.getPatientId(), doctorViewDto.getDoctorId());

        var categories = Arrays.stream(PrescriptionCategoryEnum.values())
                .map(Enum::name)
                .collect(Collectors.toList());

        var prescriptions = Arrays.stream(PrescriptionCategoryEnum.values())
                .flatMap(category -> Arrays.stream(category.getExamTypes()))
                .collect(Collectors.toList());

        model.addAttribute("categories", categories);
        model.addAttribute("prescriptions", prescriptions);
        model.addAttribute("prescriptionsDetails", prescriptionsDetails);
    }

    public void getAllDoctors(Model model) {
        var doctors = doctorAccountRepository.findAll();
        if (!doctors.isEmpty()) {
            model.addAttribute("doctorsList", doctors);
        }
    }
}
