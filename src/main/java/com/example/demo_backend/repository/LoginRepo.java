package com.example.demo_backend.repository;

import com.example.demo_backend.entity.Login;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoginRepo extends JpaRepository<Login, Long> {

    Optional<Login> findByEmail(String email);

//    Optional<Login> findByName(String name);

    Optional<Login> findByEmailAndOtp(String email, String otp);
}