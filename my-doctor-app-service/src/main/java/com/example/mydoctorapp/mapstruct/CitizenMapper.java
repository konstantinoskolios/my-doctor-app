package com.example.mydoctorapp.mapstruct;

import com.example.mydoctorapp.entities.Citizen;
import com.example.mydoctorapp.entities.PatientAccount;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CitizenMapper {

    @Mapping(target = "prescriptionsIds", ignore = true)
    @Mapping(target = "comments", ignore = true)
    @Mapping(target = "doctorId", source = "doctorId")
    PatientAccount citizenToPatientAccount(Citizen citizen, Long doctorId);
}
