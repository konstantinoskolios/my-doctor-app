package com.example.mydoctorapp.mapstruct;

import com.example.mydoctorapp.dto.DoctorAccountDto;
import com.example.mydoctorapp.entities.DoctorAccount;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DoctorMapper {

    DoctorAccountDto toDto(DoctorAccount doctorAccount);
}
