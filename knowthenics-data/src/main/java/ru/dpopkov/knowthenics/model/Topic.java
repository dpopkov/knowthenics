package ru.dpopkov.knowthenics.model;

import javax.persistence.*;

@Entity
@Table(name = "topics")
public class Topic extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "knowing_rate")
    private double knowingRate;

    @ManyToOne
    @JoinColumn(name = "qcollection_id")
    private QCollection questions;

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

    public double getKnowingRate() {
        return knowingRate;
    }

    public void setKnowingRate(double knowingRate) {
        this.knowingRate = knowingRate;
    }

    public QCollection getQuestions() {
        return questions;
    }

    public void setQuestions(QCollection questions) {
        this.questions = questions;
    }
}
