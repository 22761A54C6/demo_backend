package com.example.demo_backend.service;

import com.example.demo_backend.entity.Profile;
import com.example.demo_backend.repository.ProfileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileService {

    @Autowired
    private ProfileRepo profileRepo;

    public Profile createProfile(Profile profile) {
        return profileRepo.save(profile);
    }

    public List<Profile> getAllProfiles() {
        return profileRepo.findAll();
    }
}