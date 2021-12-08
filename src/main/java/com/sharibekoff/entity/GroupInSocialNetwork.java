package com.sharibekoff.entity;

import lombok.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.json.bind.annotation.JsonbDateFormat;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class GroupInSocialNetwork {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "group name must be specified")
    private String groupName;
    @PositiveOrZero(message = "number of subscribers shouldn't be negative")
    private Integer numberOfSubscribers;
    @JsonbDateFormat(value = "yyyy-MM-dd")
    private LocalDate createdAt;

    @PrePersist
    public void init() {
        createdAt = LocalDate.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroupInSocialNetwork that = (GroupInSocialNetwork) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
