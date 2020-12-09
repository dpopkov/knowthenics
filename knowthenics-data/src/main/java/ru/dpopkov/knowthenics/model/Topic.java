package ru.dpopkov.knowthenics.model;

public class Topic {
    private String name;
    private String description;
    private double knowingRate;
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
