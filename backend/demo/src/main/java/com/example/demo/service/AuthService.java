package com.example.demo.service;

import com.example.demo.dto.LoginRequest;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository; // 我们需要创建这个 Repository
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    public Optional<User> login(LoginRequest loginRequest) {
        Optional<User> userOpt = userRepository.findByUsername(loginRequest.getUsername());
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            // ⚠️【重要】真实项目中，绝不能这样直接比较密码！
            // 必须使用 Spring Security 和 BCryptPasswordEncoder 等工具来加密和验证密码。
            // 这里为了教学目的，暂时简化为明文比较。
            if (user.getPassword().equals(loginRequest.getPassword())) {
                return userOpt;
            }
        }
        return Optional.empty();
    }
}