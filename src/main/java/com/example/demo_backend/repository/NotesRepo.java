package com.example.demo_backend.repository;

import com.example.demo_backend.entity.Notes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotesRepo extends JpaRepository<Notes, Long> {

    List<Notes> findByUserId(Long userId);

}