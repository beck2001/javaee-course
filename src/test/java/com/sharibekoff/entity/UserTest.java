package com.sharibekoff.entity;

import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserTest {
    private Validator validator;

    @Before
    public void setUp() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    public void validUser() {
        UserInSocialNetwork user = new UserBuilderImpl()
                .id(1L)
                .firstName("Muratbek")
                .lastName("Sharibekov")
                .birthDate(LocalDate.of(2001, 9, 29))
                .city("Taraz")
                .age(20)
                .status("single")
                .build();
        Set<ConstraintViolation<UserInSocialNetwork>> cv = validator.validate(user);
        assertTrue(cv.isEmpty());
    }

    @Test
    public void invalidFirstName() {
        UserInSocialNetwork user = new UserBuilderImpl()
                .id(1L)
                .firstName("")
                .lastName("Sharibekov")
                .birthDate(LocalDate.of(2001, 9, 29))
                .city("Taraz")
                .age(20)
                .status("single")
                .build();
        Set<ConstraintViolation<UserInSocialNetwork>> cv = validator.validate(user);
        assertEquals(1, cv.size());
    }
}
