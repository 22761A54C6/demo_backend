package com.example.demo_backend.service;

import com.example.demo_backend.entity.Profile;
import com.example.demo_backend.repository.ProfileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfileService {

    @Autowired
    private ProfileRepo profileRepo;

    public Profile saveOrUpdate(Profile profile, String userEmail) {
        if (userEmail == null || userEmail.isBlank()) {
            return profileRepo.save(profile);
        }

        Profile existing = profileRepo.findByUserEmail(userEmail).orElse(null);

        if (existing == null) {
            profile.setUserEmail(userEmail);
            return profileRepo.save(profile);
        }

        if (profile.getDisplayName() != null) {
            existing.setDisplayName(profile.getDisplayName());
        }
        if (profile.getPhone() != null) {
            existing.setPhone(profile.getPhone());
        }
        if (profile.getMobile() != null) {
            existing.setMobile(profile.getMobile());
        }
        if (profile.getBio() != null) {
            existing.setBio(profile.getBio());
        }
        if (profile.getStatus() != null) {
            existing.setStatus(profile.getStatus());
        }
        if (profile.getUserId() != null) {
            existing.setUserId(profile.getUserId());
        }
        if (profile.getLoginTime() != null && !profile.getLoginTime().isBlank()) {
            existing.setLoginTime(profile.getLoginTime());
        }

        return profileRepo.save(existing);
    }

    public Optional<Profile> getByUserEmail(String userEmail) {
        return profileRepo.findByUserEmail(userEmail);
    }
}
