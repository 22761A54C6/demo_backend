package com.example.demo_backend.controllor;

import com.example.demo_backend.dto.LoginRequest;
import com.example.demo_backend.dto.UpdateProfileRequest;
import com.example.demo_backend.dto.VerifyOtpRequest;
import com.example.demo_backend.entity.Login;
import com.example.demo_backend.entity.Profile;
import com.example.demo_backend.repository.ProfileRepo;
import com.example.demo_backend.service.LoginService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/register")
    public String register(
            @Valid @RequestBody Login login
    ) {
        return loginService.register(login);
    }

    @PostMapping("/verify")
    public String verifyOtp(
            @RequestBody VerifyOtpRequest request
    ) {
        return loginService.verifyOtp(request);
    }

    @PostMapping("/login")
    public String login(
            @Valid @RequestBody LoginRequest request
    ) {
        return loginService.login(
                request.getEmail(),
                request.getPassword()
        );
    }

    @GetMapping("/dashboard/{userId}")
    public String dashboard(@PathVariable Long userId) {

        return loginService.dashboard(userId);
    }

        @PutMapping("/profile/{userId}")
        public Profile updateProfile(
                @PathVariable Long userId,
                @RequestBody UpdateProfileRequest request) {

            return loginService.updateProfile(
                    userId,
                    request
            );
        }
    }
