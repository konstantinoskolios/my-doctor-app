package com.example.mydoctorapp.mapstruct;

import com.example.mydoctorapp.entities.Citizen;
import com.example.mydoctorapp.entities.PatientAccount;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Date;

@Mapper(componentModel = "spring")
public interface CitizenMapper {

    @Mapping(target = "createdDate", source = "citizen", qualifiedByName = "mapCreatedDate")
    @Mapping(target = "comments", ignore = true)
    @Mapping(target = "doctorId", source = "doctorId")
    @Mapping(target = "id", source = "citizen.registerId",qualifiedByName = "mapRegisterId")
    PatientAccount citizenToPatientAccount(Citizen citizen, String doctorId);

    @Named(value = "mapRegisterId")
    default String mapRegisterId(String registerId) {
        return registerId;
    }

    @Named(value = "mapCreatedDate")
    default Date mapCreatedDate(Citizen citizen){
        return new Date();
    }
}
