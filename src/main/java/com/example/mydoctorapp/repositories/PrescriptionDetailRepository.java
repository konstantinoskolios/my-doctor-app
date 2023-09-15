package com.example.mydoctorapp.repositories;

import com.example.mydoctorapp.entities.PrescriptionDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PrescriptionDetailRepository extends JpaRepository<PrescriptionDetail, Long> {

    List<PrescriptionDetail> findAllByPatientIdAndDoctorId(Long patientId, Long doctorId);

    void deleteByPatientIdAndDoctorId(Long patientId, Long doctorId);
}
