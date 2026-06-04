package com.example.demo_backend.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "profile")
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String displayName;

    private String phone;

    private String bio;

    private String status;

    private LocalDateTime lastLoginTime;

    @OneToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private Login user;

    public Profile() {
    }

    public Long getId() {
        return id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getPhone() {
        return phone;
    }

    public String getBio() {
        return bio;
    }

    public String getStatus() {
        return status;
    }

    public LocalDateTime getLastLoginTime() {
        return lastLoginTime;
    }

    public Login getUser() {
        return user;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setLastLoginTime(LocalDateTime lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public void setUser(Login user) {
        this.user = user;
    }
}