package ru.dpopkov.knowthenics.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "qcollections")
public class QCollection extends BaseEntity {

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @ManyToMany
    @JoinTable(name = "qcollection_question",
            joinColumns = @JoinColumn(name = "qcollection_id"), inverseJoinColumns = @JoinColumn(name = "question_id"))
    private Set<Question> questions;
}
