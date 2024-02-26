package com.thd.cartoon.common.util.anotation;

import com.thd.cartoon.common.util.validator.ExistValueValidator;
import com.thd.cartoon.common.util.validator.FieldMatchValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author DatNuclear 22/02/2024
 * @project cartoon
 */
@Target({TYPE})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {ExistValueValidator.class})
public @interface ExistValue {
    String[] fieldName();
    int numberExist() default 1;
    String message() default "{cartoon.validation.NotNull}";
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
