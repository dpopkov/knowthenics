package ru.dpopkov.knowthenics.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sources")
public class Source extends BaseEntity {
    private String shortTitle;
    private String fullTitle;
    private String url;
    @Enumerated(EnumType.STRING)
    private SourceType sourceType;
    private String description;
}
