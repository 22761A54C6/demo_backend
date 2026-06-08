package com.example.demo_backend.repository;

import com.example.demo_backend.entity.Notes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotesRepo extends JpaRepository<Notes, Long> {

    Page<Notes> findByUserId(
            Long userId,
            Pageable pageable
    );
}