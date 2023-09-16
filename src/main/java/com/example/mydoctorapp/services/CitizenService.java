package com.example.mydoctorapp.services;

import com.example.mydoctorapp.dto.CitizenViewDTO;
import com.example.mydoctorapp.entities.Citizen;
import com.example.mydoctorapp.repositories.CitizenRepository;
import com.example.mydoctorapp.repositories.PrescriptionDetailRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import static com.example.mydoctorapp.constants.Constants.*;
import static com.example.mydoctorapp.specifications.CitizenSpecification.constructCitizenSpecification;

@Service
@Slf4j
@RequiredArgsConstructor
public class CitizenService {


    private final CitizenRepository citizenRepository;
    private final PrescriptionDetailRepository prescriptionDetailRepository;

    public String getAllCitizens(int page, int size, String sortField, String sortDir, Model model, String searchBy) {
        var pageable = PageRequest.of(page, size, sortDir.equals(SORT_ASCENDING) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending());
        var citizens = citizenRepository.findAll(constructCitizenSpecification(searchBy), pageable);
        displayItems(citizens, page, model);
        model.addAttribute(CITIZENS, citizens);
        return CITIZENS_TEMPLATE_VALUE;
    }

    private void displayItems(Page<Citizen> citizens, int page, Model model) {
        int startItem = Math.min((int) citizens.getTotalElements(), page * citizens.getSize() + ONE_VALUE);
        int endItem = Math.min((startItem + citizens.getSize() - ONE_VALUE), (int) citizens.getTotalElements());
        model.addAttribute(START_ITEM, startItem);
        model.addAttribute(END_ITEM, endItem);
    }

    public String getCitizenInformation(CitizenViewDTO citizenViewDTO) {
        return "";
    }
}
