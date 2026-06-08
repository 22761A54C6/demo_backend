package com.example.demo_backend.repository;

import com.example.demo_backend.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfileRepo extends JpaRepository<Profile, Long> {

    Optional<Profile> findByUserId(Long userId);

}


