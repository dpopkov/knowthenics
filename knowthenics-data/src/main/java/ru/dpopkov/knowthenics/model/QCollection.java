package ru.dpopkov.knowthenics.model;

import javax.persistence.*;
import java.util.Set;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
}
