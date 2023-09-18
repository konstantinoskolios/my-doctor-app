package com.example.mydoctorapp.mapstruct;

import com.example.mydoctorapp.dto.PrescriptionInformationDTO;
import com.example.mydoctorapp.entities.PrescriptionDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PrescriptionMapper {

    @Mapping(target = "prescriptionName", source = "prescriptionDetail.prescription")
    @Mapping(target = "doctorInformation", source = "doctorFullName")
    PrescriptionInformationDTO toPrescriptionInformationDTO(PrescriptionDetail prescriptionDetail, String doctorFullName);
}
