package com.example.demo.repository;

import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    // Spring Data JPA 会根据方法名自动生成查询
    Optional<User> findByUsername(String username);
}