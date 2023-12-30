package com.example.mydoctorapp.repositories;

import com.example.mydoctorapp.entities.PatientAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PatientAccountRepository extends JpaRepository<PatientAccount, String> {

    List<PatientAccount> findAllByDoctorId(String doctorId);

    Optional<PatientAccount> findByIdAndDoctorId(String patientId, String doctorId);

    boolean existsByIdAndDoctorId(String patientId, String doctorId);
}
