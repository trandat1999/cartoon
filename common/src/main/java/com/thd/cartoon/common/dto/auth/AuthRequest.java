package com.thd.cartoon.common.dto.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author DatNuclear 16/01/2024
 * @project cartoon-movie
 */
@Data
public class AuthRequest {
   @NotNull(message = "{cartoon.validation.NotNull}")
   @NotBlank(message = "{cartoon.validation.NotBlank}")
   private String username;
   @NotNull(message = "{cartoon.validation.NotNull}")
   @NotBlank(message = "{cartoon.validation.NotBlank}")
   private String password;
}
