package com.sharibekoff.service;

import com.sharibekoff.entity.UserInSocialNetwork;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
public class UserService {

    @PersistenceContext
    EntityManager entityManager;

    public UserInSocialNetwork createUser(UserInSocialNetwork user) {
        entityManager.persist(user);
        return user;
    }

    public UserInSocialNetwork updateUser(UserInSocialNetwork user) {
        entityManager.merge(user);
        return user;
    }

    public UserInSocialNetwork findById(Long id) {
        return entityManager.find(UserInSocialNetwork.class, id);
    }

    public List<UserInSocialNetwork> findAll() {
        return entityManager.createQuery("SELECT u FROM UserInSocialNetwork u", UserInSocialNetwork.class).getResultList();
    }

    public List<UserInSocialNetwork> findByAge(int age) {
        return findAll().stream()
                .filter(userInSocialNetwork -> userInSocialNetwork.getAge() > age)
                .collect(Collectors.toList());
    }

    public UserInSocialNetwork deleteUser(UserInSocialNetwork user) {
        if (!entityManager.contains(user)) {
            user = entityManager.merge(user);
        }
        entityManager.remove(user);
        return user;
    }

}
