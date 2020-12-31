package ru.dpopkov.knowthenics.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

    @Builder
    public Topic(Long id, String name, String description, double knowingRate, QCollection questions) {
        super(id);
        this.name = name;
        this.description = description;
        this.knowingRate = knowingRate;
        this.questions = questions;
    }
}
