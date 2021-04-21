package ru.dpopkov.knowthenics.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "authorities")
public class Authority extends BaseEntity {

    private String name;

    @JoinColumn(name = "user")
    @ManyToOne
    private User user;
}
