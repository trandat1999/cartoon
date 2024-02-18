package com.thd.cartoon.common.dto.auth;

import lombok.*;

/**
 * @author DatNuclear 16/01/2024
 * @project cartoon-movie
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {
    private String accessToken;
    private String refreshToken;
}
