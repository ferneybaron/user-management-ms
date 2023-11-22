package com.fbaron.core.util;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author Ferney Estupinan Baron
 * @version 0.0.1
 * @since 11/22/2023
 */
// Linking the PasswordFormatValidator class with @PasswordFormat annotation.
@Constraint(validatedBy = { PasswordFormatValidator.class })
// This constraint annotation can be used only on fields and method parameters.
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(value = RetentionPolicy.RUNTIME)
@Documented
public @interface PasswordFormat {

    // The message to return when the instance of a field annotated with @PasswordFormat fails the validation.
    String message() default "Invalid password format. Please use at least one uppercase letter, " +
            "exactly two numbers (not necessarily consecutive), and a combination of lowercase letters. " +
            "Length should be between 8 and 12 characters.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
