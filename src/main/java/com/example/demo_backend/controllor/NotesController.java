package com.example.demo_backend.controllor;

import com.example.demo_backend.entity.Notes;
import com.example.demo_backend.service.NotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

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
    public List<Notes> getUserNotes(
            @PathVariable Long userId
    ) {

        return notesService.getUserNotes(userId);
    }
}