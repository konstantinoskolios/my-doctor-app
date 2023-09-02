package com.example.mydoctorapp.repositories;

import com.example.mydoctorapp.entities.DummyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DummyEntityRepository extends JpaRepository<DummyEntity, Long> {
}
