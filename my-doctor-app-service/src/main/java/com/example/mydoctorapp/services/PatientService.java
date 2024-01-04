package com.example.mydoctorapp.services;

import com.example.mydoctorapp.entities.PrescriptionDetail;
import com.example.mydoctorapp.model.DoctorDetailsResponse;
import com.example.mydoctorapp.model.DoctorInformation;
import com.example.mydoctorapp.model.PatientPrescriptionsViewModel;
import com.example.mydoctorapp.repositories.CitizenRepository;
import com.example.mydoctorapp.repositories.PrescriptionDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static com.example.mydoctorapp.constants.Constants.USER_REGISTER_VIEW;
import static com.example.mydoctorapp.constants.Constants.YES_VALUE;
import static com.example.mydoctorapp.utils.MyDoctorAppUtils.reduceDuplicatesIds;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final CitizenRepository citizenRepository;
    private final PrescriptionDetailRepository prescriptionDetailRepository;
    private final InformationService informationService;

    public String isUserRegister(OidcUser user, Model model) {
        var userId = citizenRepository.findByRegisterId(user.getUserInfo().getSubject());

        if (userId.isPresent()) {
            model.addAttribute("patientId", userId.get().getRegisterId());
            return YES_VALUE;
        }

        return USER_REGISTER_VIEW;

    }

    public void getPrescriptions(OidcUser user, Model model) {
        var prescriptions = prescriptionDetailRepository.findAllByPatientId(user.getSubject());
        var doctorIds = reduceDuplicatesIds(prescriptions.stream().map(PrescriptionDetail::getDoctorId).toList());
        var doctorsInformation = informationService.retrieveDoctorsDetails(doctorIds);
        var prescriptionList = createPrescriptionList(prescriptions, doctorsInformation);

        model.addAttribute("prescriptionsList", prescriptionList);
    }

    private List<PatientPrescriptionsViewModel> createPrescriptionList(List<PrescriptionDetail> prescriptions, DoctorDetailsResponse doctorDetailsResponse) {
        var prescriptionList = new ArrayList<PatientPrescriptionsViewModel>();

        prescriptions.forEach(data -> {
            DoctorInformation doctorInfo = doctorDetailsResponse.doctorInformationMap().get(data.getDoctorId());

            if (doctorInfo != null) {
                prescriptionList.add(new PatientPrescriptionsViewModel(
                        doctorInfo.fullName(),
                        doctorInfo.email(),
                        data.getPrescription(),
                        data.getCategory(),
                        data.getDate()
                ));
            }
        });

        return prescriptionList;
    }



}
