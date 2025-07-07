package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import com.example.demo.entity.User;

@Data
@AllArgsConstructor // 创建一个包含所有参数的构造函数
public class LoginResponse {
    private String message;
    private String token; // 用于未来身份验证的令牌
    private Integer userId;
    private String username;
    private String email;
    private User.UserRole role;
}
