package com.example.mydoctorapp.repositories;

import com.example.mydoctorapp.entities.DoctorAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DoctorAccountRepository extends JpaRepository<DoctorAccount, Long> {
    Optional<DoctorAccount> findDoctorAccountByEmail(String email);
    Optional<DoctorAccount> findDoctorAccountByEmailAndPass(String email, String pass);

}
