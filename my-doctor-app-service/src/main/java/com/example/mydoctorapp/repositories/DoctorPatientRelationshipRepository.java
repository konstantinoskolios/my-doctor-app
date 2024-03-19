package com.example.mydoctorapp.repositories;

import com.example.mydoctorapp.entities.DoctorPatientRelationship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorPatientRelationshipRepository extends JpaRepository<DoctorPatientRelationship, Long> {

    List<DoctorPatientRelationship> findByDoctorId(String doctorId);

    void deleteByDoctorIdAndPatientId(String doctorId, String patientId);
}
