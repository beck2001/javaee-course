package com.sharibekoff.entity;

import java.time.LocalDate;

public interface UserBuilder {
    UserBuilder id(Long id);
    UserBuilder firstName(String firstName);
    UserBuilder lastName(String lastName);
    UserBuilder birthDate(LocalDate birthDate);
    UserBuilder city(String city);
    UserBuilder age(Integer age);
    UserBuilder status(String status);
    UserInSocialNetwork build();
}
