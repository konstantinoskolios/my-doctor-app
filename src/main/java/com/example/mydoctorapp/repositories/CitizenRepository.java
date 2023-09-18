package com.example.mydoctorapp.repositories;

import com.example.mydoctorapp.entities.Citizen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CitizenRepository extends JpaRepository<Citizen, Long>, JpaSpecificationExecutor<Citizen> {
    Optional<Citizen> findByTaxNumberAndSocialSecurityNumber(String taxNumber, String socialSecurityNumber);
}
