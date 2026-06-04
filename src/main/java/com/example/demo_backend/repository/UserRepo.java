package com.example.demo_backend.repository;

import com.example.demo_backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo  extends JpaRepository<User, Long> {
}
//
//
//package com.example.demo_backend.repository;
//
//import com.example.demo_backend.entity.User;
//import org.springframework.data.jpa.repository.JpaRepository;
//
//public interface UserRepo extends JpaRepository<User, Long> {
//
//}