package com.example.demo_backend.controllor;

import com.example.demo_backend.entity.Profile;
import com.example.demo_backend.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @PostMapping
    public Profile saveProfile(
            @RequestBody Profile profile,
            @RequestHeader(value = "X-User-Email", defaultValue = "") String userEmail
    ) {
        return profileService.saveOrUpdate(profile, userEmail);
    }

    @GetMapping
    public Profile getProfile(
            @RequestHeader(value = "X-User-Email", defaultValue = "") String userEmail
    ) {
        return profileService.getByUserEmail(userEmail).orElse(new Profile());
    }
}
