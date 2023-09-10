package com.example.mydoctorapp.repositories;

import com.example.mydoctorapp.entities.PatientAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PatientAccountRepository extends JpaRepository<PatientAccount, Long> {

    List<PatientAccount> findAllByDoctorId(Long doctorId);
    Optional<PatientAccount> findByIdAndDoctorId(Long patientId, Long doctorId);
}
