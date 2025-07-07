package com.example.demo.controller;

import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.LoginResponse;
import com.example.demo.entity.User;
import com.example.demo.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        Optional<User> userOptional = authService.login(loginRequest);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            // 登录成功，生成一个假的 token
            String token = UUID.randomUUID().toString();
            LoginResponse response = new LoginResponse(
                "登录成功", 
                token, 
                user.getUserId(), 
                user.getUsername(),
                user.getEmail(),
                user.getRole()
            );
            return ResponseEntity.ok(response);
        } else {
            // 登录失败
            return ResponseEntity.status(401).body("用户名或密码错误");
        }
    }
}
