package com.sharibekoff.service;

import com.sharibekoff.entity.GroupInSocialNetwork;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
public class GroupService {

    @PersistenceContext
    EntityManager entityManager;

    public GroupInSocialNetwork createGroup(GroupInSocialNetwork group) {
        entityManager.persist(group);
        return group;
    }

    public GroupInSocialNetwork updateGroup(GroupInSocialNetwork group) {
        entityManager.merge(group);
        return group;
    }

    public GroupInSocialNetwork findById(Long id) {
        return entityManager.find(GroupInSocialNetwork.class, id);
    }

    public List<GroupInSocialNetwork> findAll() {
        return entityManager.createQuery("SELECT g FROM GroupInSocialNetwork g", GroupInSocialNetwork.class).getResultList();
    }

    public List<GroupInSocialNetwork> findByNumberOfSubscribers(int amount) {
        return findAll().stream()
                .filter(userInSocialNetwork -> userInSocialNetwork.getNumberOfSubscribers() > amount)
                .collect(Collectors.toList());
    }

    public GroupInSocialNetwork deleteGroup(GroupInSocialNetwork group) {
        if (!entityManager.contains(group)) {
            group = entityManager.merge(group);
        }
        entityManager.remove(group);
        return group;
    }
}
