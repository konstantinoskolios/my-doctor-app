package com.example.mydoctorapp.repositories;

import com.example.mydoctorapp.entities.PrescriptionDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrescriptionDetailRepository extends JpaRepository<PrescriptionDetail, Long> {
}
