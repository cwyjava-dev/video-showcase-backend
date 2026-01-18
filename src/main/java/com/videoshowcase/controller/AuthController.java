package com.videoshowcase.controller;

import com.videoshowcase.dto.AuthRequest;
import com.videoshowcase.dto.AuthResponse;
import com.videoshowcase.dto.UserDto;
import com.videoshowcase.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "认证管理", description = "用户登录、注册等认证相关接口")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    @Operation(summary = "用户登录")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody AuthRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/register")
    @Operation(summary = "用户注册")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody AuthRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @GetMapping("/me")
    @Operation(summary = "获取当前用户信息")
    public ResponseEntity<UserDto> getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userId = auth.getName();
        return ResponseEntity.ok(authService.getCurrentUser(Long.parseLong(userId)));
    }

    @PostMapping("/logout")
    @Operation(summary = "用户登出")
    public ResponseEntity<String> logout() {
        return ResponseEntity.ok("登出成功");
    }
}
