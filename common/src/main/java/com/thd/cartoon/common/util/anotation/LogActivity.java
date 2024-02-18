package com.thd.cartoon.common.util.anotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author DatNuclear 22/01/2024
 * @project cartoon-movie
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface LogActivity {
    String m() default "";
}
