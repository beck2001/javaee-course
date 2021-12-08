package com.sharibekoff.entity;

import java.time.LocalDate;

public class GroupBuilderImpl implements GroupBuilder {
    GroupInSocialNetwork group = new GroupInSocialNetwork();

    @Override
    public GroupBuilder id(Long id) {
        group.setId(id);
        return this;
    }

    @Override
    public GroupBuilder groupName(String groupName) {
        group.setGroupName(groupName);
        return this;
    }

    @Override
    public GroupBuilder numberOfSubscribers(Integer numberOfSubscribers) {
        group.setNumberOfSubscribers(numberOfSubscribers);
        return this;
    }

    @Override
    public GroupBuilder createdAt(LocalDate createdAt) {
        group.setCreatedAt(createdAt);
        return this;
    }

    @Override
    public GroupInSocialNetwork build() {
        return group;
    }
}
