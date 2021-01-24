package ru.dpopkov.knowthenics.model;

import lombok.*;
import org.hibernate.validator.constraints.URL;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sources")
public class Source extends BaseEntity {
    @Size(min = 3, max = 128)
    @NotBlank
    private String shortTitle;
    @Size(max = 255)
    private String fullTitle;
    @URL
    @NotBlank
    private String url;
    @Enumerated(EnumType.STRING)
    private SourceType sourceType;
    @Size(max = 255)
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

    @Override
    public String toString() {
        return shortTitle;
    }
}
