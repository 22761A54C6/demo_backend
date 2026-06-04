package com.example.demo_backend.controllor;

import com.example.demo_backend.entity.User;
import com.example.demo_backend.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService service;

    // POST
    @PostMapping
    public User addUser(@RequestBody User user) {
        return service.addUser(user);
    }

    // GET ALL
    @GetMapping
    public List<User> getUsers() {
        return service.getUsers();
    }

    // GET BY ID
    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return service.getUser(id);
    }

    // UPDATE
    @PutMapping("/{id}")
    public User updateUser(
            @PathVariable Long id,
            @RequestBody User user
    ) {
        return service.updateUser(id, user);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        return service.deleteUser(id);
    }
}