package com.example.mydoctorapp.repositories;

import com.example.mydoctorapp.entities.DoctorAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorAccountRepository extends JpaRepository<DoctorAccount, String> {
    @Query(value = "SELECT COUNT(d.id) FROM doctor_account d WHERE CAST(d.created_date AS DATE) >= CAST(:startDate AS DATE) AND CAST(d.created_date AS DATE) <= CAST(:endDate AS DATE)", nativeQuery = true)
    long countByCreatedDateGreaterThanEqualAndCreatedDateLessThanEqual(String startDate, String endDate);

}
