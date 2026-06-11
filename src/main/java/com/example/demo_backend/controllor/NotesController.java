package com.example.demo_backend.controllor;

import com.example.demo_backend.entity.Notes;
import com.example.demo_backend.service.NotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/notes")
public class NotesController {

    @Autowired
    private NotesService notesService;

    @PostMapping("/upload")
    public String uploadFile(
            @RequestParam("file") MultipartFile file
    ) throws IOException {

        return notesService.uploadFile(file);
    }

    @GetMapping("/getnotes")
    public List<Notes> getAllNotes() {
        return notesService.getAllNotes();
    }
}