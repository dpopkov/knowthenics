package ru.dpopkov.knowthenics.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User extends BaseEntity {

    private String username;
    private String password;

    @Enumerated(EnumType.STRING)
    private EncryptionAlgorithm algorithm;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private Set<Authority> authorities = new HashSet<>();

    public User(String username, String password, EncryptionAlgorithm algorithm) {
        this.username = username;
        this.password = password;
        this.algorithm = algorithm;
    }

    public void addAuthority(Authority authority) {
        authorities.add(authority);
    }
}
