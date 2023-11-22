package com.fbaron.core.util;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

/**
 * PasswordFormatValidator class is used to validate the password format.
 * <p>
 * PasswordFormatValidator is a custom validator class that implements the ConstraintValidator interface.
 * It is used to validate the password format of a user's password. The password format must contain only one uppercase
 * letter, only two numbers, and be between 8 and 12 characters long.
 *
 * @author Ferney Estupinan Baron
 * @version 0.0.1
 * @since 11/22/2023
 */
public class PasswordFormatValidator implements ConstraintValidator<PasswordFormat, String> {

    private static final Pattern ONE_UPPERCASE_LETTER_PATTERN = Pattern.compile("^[^A-Z]*[A-Z][^A-Z]*$");
    private static final Pattern TWO_NUMBERS_PATTERN = Pattern.compile("^\\D*\\d\\D*\\d\\D*$");

    /**
     * Implements the validation logic.
     * The state of {@code value} must not be altered.
     * <p>
     * This method can be accessed concurrently, thread-safety must be ensured
     * by the implementation.
     *
     * @param value   object to validate
     * @param context context in which the constraint is evaluated
     * @return {@code false} if {@code value} does not pass the constraint
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null &&
                !value.isEmpty() &&
                ONE_UPPERCASE_LETTER_PATTERN.matcher(value).matches() &&
                TWO_NUMBERS_PATTERN.matcher(value).matches() &&
                value.length() >= 8
                && value.length() <= 12;
    }

}
