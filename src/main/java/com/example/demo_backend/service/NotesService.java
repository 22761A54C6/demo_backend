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

    public String uploadFile(MultipartFile file) throws IOException {

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

        notesRepo.save(notes);

        return "File uploaded successfully";
    }

    public List<Notes> getAllNotes() {
        return notesRepo.findAll();
    }
}