package com.shepherd.basedemo.validator;

import org.intellij.lang.annotations.Language;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2025/5/12
 */
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {CombineNotNullValidator.class})
public @interface CombineNotNull {
    String message() default "enum value is not valid";

    /** Spring SpEL表达式   */
    @Language("SpEL")
    String condition() default "";


    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
