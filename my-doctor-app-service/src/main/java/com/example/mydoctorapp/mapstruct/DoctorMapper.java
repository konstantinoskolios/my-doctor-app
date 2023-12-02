package com.example.mydoctorapp.mapstruct;

import com.example.mydoctorapp.dto.DoctorAccountDTO;
import com.example.mydoctorapp.entities.DoctorAccount;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DoctorMapper {

    DoctorAccountDTO toDto(DoctorAccount doctorAccount);
}
