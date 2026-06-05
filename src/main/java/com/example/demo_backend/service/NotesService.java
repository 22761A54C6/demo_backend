package com.example.demo_backend.service;

import com.example.demo_backend.entity.Login;
import com.example.demo_backend.entity.Notes;
import com.example.demo_backend.repository.LoginRepo;
import com.example.demo_backend.repository.NotesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotesService {

    @Autowired
    private NotesRepo notesRepo;

    @Autowired
    private LoginRepo loginRepo;

    public String uploadFile(
            Long userId,
            MultipartFile file
    ) throws IOException {

        if (file.isEmpty()) {
            return "Please Select a File";
        }

        Login user = loginRepo
                .findById(userId)
                .orElse(null);

        if (user == null) {
            return "User Not Found";
        }

        String uploadDir =
                "C:/Users/Admin/OneDrive/Desktop/Storeing files/";

        File folder = new File(uploadDir);

        if (!folder.exists()) {
            folder.mkdirs();
        }

        String fileName = file.getOriginalFilename();

        String filePath =
                uploadDir + fileName;

        file.transferTo(new File(filePath));

        Notes note = new Notes();

        note.setFileName(file.getOriginalFilename());
        note.setFileType(file.getContentType());
        note.setFileSize(file.getSize());
        note.setFilePath(filePath);
        note.setUploadTime(LocalDateTime.now());
        note.setUser(user);

        notesRepo.save(note);

        return "File Uploaded Successfully";
    }

    public List<Notes> getUserNotes(Long userId) {

        return notesRepo.findByUserId(userId);
    }
}