package com.example.mydoctorapp.repositories;

import com.example.mydoctorapp.entities.PrescriptionDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrescriptionDetailRepository extends JpaRepository<PrescriptionDetail, String> {

    List<PrescriptionDetail> findAllByPatientIdAndDoctorId(String patientId, String doctorId);

    void deleteByPatientIdAndDoctorId(String patientId, String doctorId);

    List<PrescriptionDetail> findAllByPatientId(String id);
}
