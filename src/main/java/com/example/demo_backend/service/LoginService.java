package com.example.demo_backend.service;

import com.example.demo_backend.dto.VerifyOtpRequest;
import com.example.demo_backend.entity.Login;
import com.example.demo_backend.entity.Profile;
import com.example.demo_backend.repository.LoginRepo;
import com.example.demo_backend.repository.ProfileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo_backend.dto.UpdateProfileRequest;
import java.time.LocalDateTime;
import java.util.Random;

@Service
public class LoginService {

    @Autowired
    private LoginRepo loginRepo;

    @Autowired
    private ProfileRepo profileRepo;

    public String register(Login login) {

        if (loginRepo.findByEmail(login.getEmail()).isPresent()) {
            return "Email already exists";
        }

        String otp = String.valueOf(
                100000 + new Random().nextInt(900000)
        );

        login.setOtp(otp);
        login.setVerified(false);

        loginRepo.save(login);

        System.out.println("OTP = " + otp);

        return "OTP Sent Successfully";
    }

    public String verifyOtp(VerifyOtpRequest request) {

        Login user = loginRepo
                .findByEmailAndOtp(
                        request.getEmail(),
                        request.getOtp()
                )
                .orElse(null);

        if (user == null) {
            return "Invalid OTP";
        }

        user.setVerified(true);
        user.setOtp(null);

        loginRepo.save(user);

        return "Email Verified Successfully";
    }

    public String login(String email, String password) {

        Login user = loginRepo
                .findByEmail(email)
                .orElse(null);

        if (user == null) {
            return "User Not Found";
        }

        if (!user.isVerified()) {
            return "Verify Email First";
        }

        if (!user.getPassword().equals(password)) {
            return "Invalid Password";
        }

        Profile profile = profileRepo
                .findByUserId(user.getId())
                .orElse(null);

        if (profile == null) {

            profile = new Profile();

            profile.setDisplayName(user.getName());
            profile.setPhone("");
            profile.setBio("");
            profile.setStatus("Logged In");
            profile.setLastLoginTime(LocalDateTime.now());

            profile.setUser(user);

            profileRepo.save(profile);

        } else {

            profile.setStatus("Logged In");
            profile.setLastLoginTime(LocalDateTime.now());

            profileRepo.save(profile);
        }

        return "Login Success";
    }

    public String dashboard(Long userId) {

        Profile profile = profileRepo
                .findByUserId(userId)
                .orElse(null);

        if(profile == null) {
            return "Profile Not Found";
        }

        return "Welcome " + profile.getDisplayName();
    }

    public Profile updateProfile(
            Long userId,
            UpdateProfileRequest request) {

        Profile profile = profileRepo
                .findByUserId(userId)
                .orElse(null);

        if (profile == null) {
            return null;
        }

        profile.setDisplayName(request.getDisplayName());
        profile.setPhone(request.getPhone());
        profile.setBio(request.getBio());

        return profileRepo.save(profile);
    }
}