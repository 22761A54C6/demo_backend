package com.example.demo_backend.repository;

import com.example.demo_backend.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepo extends JpaRepository<Profile, Long> {
}