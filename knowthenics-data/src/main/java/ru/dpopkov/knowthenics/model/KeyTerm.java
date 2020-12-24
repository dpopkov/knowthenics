package ru.dpopkov.knowthenics.model;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "key_terms")
public class KeyTerm extends BaseEntity {
    private String name;
    private String description;

    public KeyTerm() {
    }

    public KeyTerm(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @ManyToMany(mappedBy = "keyTerms")
    private Set<Question> questions = new HashSet<>();

    @ManyToMany(mappedBy = "keyTerms")
    private Set<Answer> answers = new HashSet<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<Question> questions) {
        this.questions = questions;
    }

    public Set<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(Set<Answer> answers) {
        this.answers = answers;
    }
}
