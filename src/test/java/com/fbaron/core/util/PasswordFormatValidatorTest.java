package com.fbaron.core.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.validation.ConstraintValidatorContext;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class PasswordFormatValidatorTest {

    @Mock
    private ConstraintValidatorContext constraintValidatorContext;

    @InjectMocks
    private PasswordFormatValidator underTest;

    @Test
    void isValid_shouldReturnTrueForValidPassword() {
        assertTrue(underTest.isValid("a2asfGfdfdf4", constraintValidatorContext));
    }

    @Test
    void isValid_shouldReturnFalseForNullPassword() {
        assertFalse(underTest.isValid(null, constraintValidatorContext));
    }

    @Test
    void isValid_shouldReturnFalseForEmptyPassword() {
        assertFalse(underTest.isValid("", constraintValidatorContext));
    }

    @Test
    void isValid_shouldReturnFalseForPasswordWithMoreThanOneUppercaseLetter() {
        assertFalse(underTest.isValid("Ab12cDef", constraintValidatorContext));
    }

    @Test
    void isValid_shouldReturnFalseForPasswordWithNoUppercaseLetter() {
        assertFalse(underTest.isValid("ab12cdef", constraintValidatorContext));
    }

    @Test
    void isValid_shouldReturnFalseForPasswordWithLessThanTwoNumbers() {
        assertFalse(underTest.isValid("a2asfGs", constraintValidatorContext));
    }

    @Test
    void isValid_shouldReturnFalseForPasswordWithMoreThanTwoNumbers() {
        assertFalse(underTest.isValid("a2a5fG4", constraintValidatorContext));
    }

    @Test
    void isValid_shouldReturnFalseForShortPassword() {
        assertFalse(underTest.isValid("a2asfG4", constraintValidatorContext));
    }

    @Test
    void isValid_shouldReturnFalseForLongPassword() {
        assertFalse(underTest.isValid("a2asfGfdfdf4s", constraintValidatorContext));
    }

}