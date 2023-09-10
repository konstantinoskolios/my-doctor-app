package com.example.mydoctorapp.models;

import com.example.mydoctorapp.entities.PatientAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientAccountRepository extends JpaRepository<PatientAccount, Long> {

}
