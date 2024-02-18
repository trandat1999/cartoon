package com.thd.cartoon.business.service;

import com.thd.cartoon.common.dto.auth.AuthRequest;
import com.thd.cartoon.common.dto.auth.ForgotPasswordRequest;
import com.thd.cartoon.common.dto.auth.RegisterRequest;
import com.thd.cartoon.common.dto.response.BaseResponse;

/**
 * @author DatNuclear 16/01/2024
 * @project cartoon-movie
 */
public interface AuthService {
    BaseResponse login(AuthRequest request);
    BaseResponse register(RegisterRequest request);
    BaseResponse verifyAccount(String token);
    BaseResponse generateActiveToken(String username);
    BaseResponse forgotPassword(ForgotPasswordRequest request);
    BaseResponse activeNewPassword(String token);
    BaseResponse generateNewToken(String token);
    BaseResponse refreshToken(String token);
}
