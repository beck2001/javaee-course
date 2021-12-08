package com.sharibekoff.entity;

import java.time.LocalDate;

public class UserBuilderImpl implements UserBuilder {
    UserInSocialNetwork user = new UserInSocialNetwork();

    @Override
    public UserBuilder id(Long id) {
        user.setId(id);
        return this;
    }

    @Override
    public UserBuilder firstName(String firstName) {
        user.setFirstName(firstName);
        return this;
    }

    @Override
    public UserBuilder lastName(String lastName) {
        user.setLastName(lastName);
        return this;
    }

    @Override
    public UserBuilder birthDate(LocalDate birthDate) {
        user.setBirthDate(birthDate);
        return this;
    }

    @Override
    public UserBuilder city(String city) {
        user.setCity(city);
        return this;
    }

    @Override
    public UserBuilder age(Integer age) {
        user.setAge(age);
        return this;
    }

    @Override
    public UserBuilder status(String status) {
        user.setStatus(status);
        return this;
    }

    @Override
    public UserInSocialNetwork build() {
        return user;
    }
}
