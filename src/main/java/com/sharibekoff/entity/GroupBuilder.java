package com.sharibekoff.entity;

import java.time.LocalDate;

public interface GroupBuilder {
    GroupBuilder id(Long id);
    GroupBuilder groupName(String groupName);
    GroupBuilder numberOfSubscribers(Integer numberOfSubscribers);
    GroupBuilder createdAt(LocalDate createdAt);
    GroupInSocialNetwork build();
}
