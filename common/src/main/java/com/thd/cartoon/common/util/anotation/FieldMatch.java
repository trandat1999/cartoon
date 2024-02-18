package com.thd.cartoon.common.util.anotation;

import com.thd.cartoon.common.util.validator.FieldMatchValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author DatNuclear 19/01/2024
 * @project cartoon-movie
 */
@Target({TYPE})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {FieldMatchValidator.class})
public @interface FieldMatch {
    String fieldName();
    String dependFieldName();
    String message() default "{error.password.mismatch}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
