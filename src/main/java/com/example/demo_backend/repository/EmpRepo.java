package com.example.demo_backend.repository;

import com.example.demo_backend.entity.Emp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpRepo extends JpaRepository<Emp, Long> {
}
