package com.sharibekoff.entity;

import lombok.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.json.bind.annotation.JsonbDateFormat;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class UserInSocialNetwork {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "first name shouldn't be empty")
    private String firstName;
    @NotEmpty(message = "last name shouldn't be empty")
    private String lastName;
    @NotNull(message = "birth date should be provided")
    @JsonbDateFormat(value = "yyyy-MM-dd")
    private LocalDate birthDate;
    @NotEmpty(message = "city shouldn't be empty")
    private String city;
    @Positive(message = "age should be positive")
    private Integer age;
    private String status;

    @Column(length = 10, nullable = false)
    private String login;
    @Column(length = 256, nullable = false)
    private String password;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserInSocialNetwork that = (UserInSocialNetwork) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
