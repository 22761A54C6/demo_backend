package com.example.demo_backend.controllor;

import com.example.demo_backend.entity.Notes;
import com.example.demo_backend.service.NotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;

@RestController
@RequestMapping("/auth/dashboard/notes")
public class NotesController {

    @Autowired
    private NotesService notesService;

    @PostMapping("/upload/{userId}")
    public String uploadFile(
            @PathVariable Long userId,
            @RequestParam("file") MultipartFile file
    ) throws IOException {
        return notesService.uploadFile(userId, file);
    }


    @GetMapping("/{userId}")
    public Page<Notes> getUserNotes(
            @PathVariable Long userId,
            @RequestParam(defaultValue = "0")
            int page,
            @RequestParam(defaultValue = "5")
            int size
    ) {
        return notesService.getUserNotes(
                userId,
                page,
                size
        );
    }
}