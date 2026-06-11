package com.example.demo_backend.repository;

import com.example.demo_backend.entity.Notes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotesRepo extends JpaRepository<Notes, Long> {
}