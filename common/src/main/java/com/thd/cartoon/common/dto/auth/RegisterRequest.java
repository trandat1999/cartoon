package com.thd.cartoon.common.dto.auth;

import com.thd.cartoon.common.util.ConstUtil;
import com.thd.cartoon.common.util.SystemVariable;
import com.thd.cartoon.common.util.anotation.FieldMatch;
import jakarta.validation.constraints.*;
import lombok.Data;

import static com.thd.cartoon.common.util.ConstUtil.MIN_LENGTH_PASSWORD_REQUIRED;
import static com.thd.cartoon.common.util.SystemMessage.*;

/**
 * @author DatNuclear 19/01/2024
 * @project cartoon-movie
 */
@Data
@FieldMatch(message = VALIDATION_FIELD_MATCH,
        fieldName = SystemVariable.CONFIRM_PASSWORD,
        dependFieldName = SystemVariable.PASSWORD)
public class RegisterRequest {
    @Pattern(regexp = ConstUtil.PATTERN_USERNAME, message = VALIDATION_USERNAME_PATTERN)
    @NotNull(message = VALIDATION_NOTNULL)
    @NotBlank(message = VALIDATION_NOT_BLANK)
    private String username;
    @NotNull(message = VALIDATION_NOTNULL)
    @Size(min = MIN_LENGTH_PASSWORD_REQUIRED, message = VALIDATION_MIN_LENGTH)
    private String password;
    @NotNull(message = VALIDATION_NOTNULL)
    @Size(min = MIN_LENGTH_PASSWORD_REQUIRED, message = VALIDATION_MIN_LENGTH)
    private String confirmPassword;
    @Email(message = VALIDATION_EMAIL)
    @NotNull(message = VALIDATION_NOTNULL)
    @NotBlank(message = VALIDATION_NOT_BLANK)
    private String email;
    @NotNull(message = VALIDATION_NOTNULL)
    @NotBlank(message = VALIDATION_NOT_BLANK)
    private String fullName;
}
