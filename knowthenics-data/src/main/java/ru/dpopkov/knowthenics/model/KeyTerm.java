package ru.dpopkov.knowthenics.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "key_terms")
public class KeyTerm extends BaseEntity {
    @NotBlank
    @Size(min = 2, max = 64)
    private String name;
    @Size(max = 255)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof KeyTerm)) {
            return false;
        }
        KeyTerm keyTerm = (KeyTerm) o;
        Long id = getId();
        Long otherId = keyTerm.getId();
        if (id == null && otherId != null || id != null & otherId == null) {
            return false;
        } else {
            return (id != null && id.equals(otherId)) && Objects.equals(getName(), keyTerm.getName());
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }
}
