package ru.dpopkov.knowthenics.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "key_terms")
public class KeyTerm extends BaseEntity {
    private String name;
    private String description;

    @ManyToMany(mappedBy = "keyTerms")
    private Set<Question> questions = new HashSet<>();

    @ManyToMany(mappedBy = "keyTerms")
    private Set<Answer> answers = new HashSet<>();

    public KeyTerm(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @Builder
    public KeyTerm(Long id, String name, String description, Set<Question> questions, Set<Answer> answers) {
        super(id);
        this.name = name;
        this.description = description;
        this.questions = questions != null ? questions : new HashSet<>();
        this.answers = answers != null ? answers : new HashSet<>();
    }
}
