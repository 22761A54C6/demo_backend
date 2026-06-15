package com.example.demo_backend.controllor;



import com.example.demo_backend.entity.Notes;

import com.example.demo_backend.service.NotesService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.core.io.Resource;

import org.springframework.core.io.UrlResource;

import org.springframework.http.HttpHeaders;

import org.springframework.http.HttpStatus;

import org.springframework.http.MediaType;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;



import java.io.IOException;

import java.nio.file.Path;

import java.util.List;



@RestController

@RequestMapping("/notes")

public class NotesController {



    @Autowired

    private NotesService notesService;



    @PostMapping("/upload")

    public String uploadFile(

            @RequestParam("file") MultipartFile file,

            @RequestHeader(value = "X-User-Email", defaultValue = "") String uploadedBy

    ) throws IOException {



        return notesService.uploadFile(file, uploadedBy);

    }



    @GetMapping("/getnotes")

    public List<Notes> getAllNotes() {

        return notesService.getAllNotes();

    }



    @GetMapping("/download/{id}")

    public ResponseEntity<Resource> downloadFile(@PathVariable Long id) throws IOException {

        Notes note = notesService.getNoteById(id);

        Path path = notesService.getFilePath(id);

        Resource resource = new UrlResource(path.toUri());



        String contentType = note.getFileType();

        if (contentType == null || contentType.isBlank()) {

            contentType = MediaType.APPLICATION_OCTET_STREAM_VALUE;

        }



        return ResponseEntity.ok()

                .contentType(MediaType.parseMediaType(contentType))

                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + note.getFileName() + "\"")

                .body(resource);

    }



    @DeleteMapping("/delete/{id}")

    public ResponseEntity<String> deleteFile(

            @PathVariable Long id,

            @RequestHeader(value = "X-User-Email", defaultValue = "") String currentUser

    ) {

        try {

            notesService.deleteNote(id, currentUser);

            return ResponseEntity.ok("File deleted successfully");

        } catch (RuntimeException e) {

            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());

        } catch (IOException e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Could not delete file");

        }

    }

}


