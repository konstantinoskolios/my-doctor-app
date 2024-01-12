package com.example.mydoctorapp.repositories;

import com.example.mydoctorapp.entities.PatientAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PatientAccountRepository extends JpaRepository<PatientAccount, String> {

    List<PatientAccount> findAllByDoctorId(String doctorId);

    Optional<PatientAccount> findByIdAndDoctorId(String patientId, String doctorId);

    boolean existsByIdAndDoctorId(String patientId, String doctorId);

    @Query(value = "SELECT COUNT(pa1_0.id) FROM patient_account pa1_0 WHERE CAST(pa1_0.created_date AS DATE) >= CAST(:startDate AS DATE) AND CAST(pa1_0.created_date AS DATE) <= CAST(:endDate AS DATE)", nativeQuery = true)
    long countByCreatedDateGreaterThanEqualAndCreatedDateLessThanEqual(String startDate, String endDate);
}
