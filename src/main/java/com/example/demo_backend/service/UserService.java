package com.example.demo_backend.service;

import com.example.demo_backend.entity.User;
import com.example.demo_backend.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepo repo;

    // CREATE
    public User addUser(User user) {
        return repo.save(user);
    }

    // GET ALL
    public List<User> getUsers() {
        return repo.findAll();
    }

    // GET BY ID
    public User getUser(Long id) {
        return repo.findById(id).orElse(null);
    }

    // UPDATE
    public User updateUser(Long id, User user) {

        User existUser = repo.findById(id).orElse(null);

        if (existUser != null) {
            existUser.setName(user.getName());
            existUser.setEmail(user.getEmail());

            return repo.save(existUser);
        }

        return null;
    }

    // DELETE
    public String deleteUser(Long id) {

        repo.deleteById(id);

        return "User Deleted Successfully";
    }
}