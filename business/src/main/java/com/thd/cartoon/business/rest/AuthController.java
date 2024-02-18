package com.thd.cartoon.business.rest;

import com.thd.cartoon.common.dto.auth.AuthRequest;
import com.thd.cartoon.common.dto.auth.ForgotPasswordRequest;
import com.thd.cartoon.common.dto.auth.RegisterRequest;
import com.thd.cartoon.common.dto.response.BaseResponse;
import com.thd.cartoon.business.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author DatNuclear 16/01/2024
 * @project cartoon-movie
 */

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthService authService;
    @PostMapping("/login")
    public ResponseEntity<BaseResponse> login(@RequestBody AuthRequest request){
        return ResponseEntity.ok(authService.login(request));
    }
    @PostMapping("/register")
    public ResponseEntity<BaseResponse> register(@RequestBody RegisterRequest request){
        return ResponseEntity.ok(authService.register(request));
    }
    @GetMapping("/verification/{token}")
    public ResponseEntity<BaseResponse> verifyAccount(@PathVariable("token") String token){
        return ResponseEntity.ok(authService.verifyAccount(token));
    }
    @GetMapping("/generation-active-token/{username}")
    public ResponseEntity<BaseResponse> generateActiveToken(@PathVariable("username") String username){
        return ResponseEntity.ok(authService.generateActiveToken(username));
    }
    @PostMapping("/forgot-password")
    public ResponseEntity<BaseResponse> register(@RequestBody ForgotPasswordRequest request){
        return ResponseEntity.ok(authService.forgotPassword(request));
    }
    @GetMapping("/forgot-password/{token}")
    public ResponseEntity<BaseResponse> activePassword(@PathVariable("token") String token) {
        return ResponseEntity.ok(authService.activeNewPassword(token));
    }
    @GetMapping("/forgot-password/new-token/{token}")
    public ResponseEntity<BaseResponse> generateNewToken(@PathVariable("token") String token) {
        return ResponseEntity.ok(authService.generateNewToken(token));
    }
    @GetMapping("/refresh-token/{token}")
    public ResponseEntity<BaseResponse> refreshToken(@PathVariable("token") String token) {
        return ResponseEntity.ok(authService.refreshToken(token));
    }
}
