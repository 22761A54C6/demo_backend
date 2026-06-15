package com.example.demo_backend.service;



import com.example.demo_backend.entity.Notes;

import com.example.demo_backend.repository.NotesRepo;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import org.springframework.web.multipart.MultipartFile;



import java.io.IOException;

import java.nio.file.*;

import java.util.List;



@Service

public class NotesService {



    @Autowired

    private NotesRepo notesRepo;



    private static final String UPLOAD_DIR =

            "C:\\Users\\Admin\\OneDrive\\Desktop\\Storeing files";



    public String uploadFile(MultipartFile file, String uploadedBy) throws IOException {



        Path uploadPath = Paths.get(UPLOAD_DIR);



        if (!Files.exists(uploadPath)) {

            Files.createDirectories(uploadPath);

        }



        String fileName = file.getOriginalFilename();



        assert fileName != null;

        Path filePath = uploadPath.resolve(fileName);



        Files.copy(

                file.getInputStream(),

                filePath,

                StandardCopyOption.REPLACE_EXISTING

        );



        Notes notes = new Notes();

        notes.setFileName(fileName);

        notes.setFileType(file.getContentType());

        notes.setFilePath(filePath.toString());

        notes.setUploadedBy(uploadedBy);



        notesRepo.save(notes);



        return "File uploaded successfully";

    }



    public List<Notes> getAllNotes() {

        return notesRepo.findAll();

    }



    public Notes getNoteById(Long id) {

        return notesRepo.findById(id)

                .orElseThrow(() -> new RuntimeException("File not found"));

    }



    public Path getFilePath(Long id) {

        Notes note = getNoteById(id);

        Path path = Paths.get(note.getFilePath());



        if (!Files.exists(path)) {

            throw new RuntimeException("File not found on disk");

        }



        return path;

    }



    public void deleteNote(Long id, String currentUser) throws IOException {

        Notes note = getNoteById(id);



        if (note.getUploadedBy() == null || note.getUploadedBy().isBlank()) {

            throw new RuntimeException("You can only delete your own files");

        }



        if (!note.getUploadedBy().equals(currentUser)) {

            throw new RuntimeException("You can only delete your own files");

        }



        Path path = Paths.get(note.getFilePath());



        if (Files.exists(path)) {

            Files.delete(path);

        }



        notesRepo.deleteById(id);

    }

}


