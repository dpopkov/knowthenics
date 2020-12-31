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

    @Builder
    public Source(Long id, String shortTitle, String fullTitle, String url, SourceType sourceType, String description) {
        super(id);
        this.shortTitle = shortTitle;
        this.fullTitle = fullTitle;
        this.url = url;
        this.sourceType = sourceType;
        this.description = description;
    }
}
