package com.example.mydoctorapp.repositories;

import com.example.mydoctorapp.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findAccountByEmailAndPass(String email, String pass);

    Optional<Account> findAccountByEmail(String email);
}
