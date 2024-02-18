package com.thd.cartoon.common.dto.auth;

import com.thd.cartoon.common.util.ConstUtil;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import static com.thd.cartoon.common.util.SystemMessage.*;

/**
 * @author DatNuclear 21/01/2024
 * @project cartoon-movie
 */
@Data
public class ForgotPasswordRequest {
    @Pattern(regexp = ConstUtil.PATTERN_USERNAME, message = VALIDATION_USERNAME_PATTERN)
    @NotNull(message = VALIDATION_NOTNULL)
    @NotBlank(message = VALIDATION_NOT_BLANK)
    private String username;
    @Email(message = VALIDATION_EMAIL)
    @NotNull(message = VALIDATION_NOTNULL)
    @NotBlank(message = VALIDATION_NOT_BLANK)
    private String email;
}
