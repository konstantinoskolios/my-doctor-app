package com.example.mydoctorapp.repositories;

import com.example.mydoctorapp.entities.DoctorPatientRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorPatientRelationRepository extends JpaRepository<DoctorPatientRelation, Long> {

    List<DoctorPatientRelation> findAllByDoctorId(Long doctorId);
}
