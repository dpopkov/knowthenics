package ru.dpopkov.knowthenics.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class SourceDTO extends BaseDTO {
    private String shortTitle;
    private String fullTitle;
    private String url;
    private String sourceType;
    private String description;

    @Builder
    public SourceDTO(Long id, String shortTitle, String fullTitle, String url, String sourceType, String description) {
        super(id);
        this.shortTitle = shortTitle;
        this.fullTitle = fullTitle;
        this.url = url;
        this.sourceType = sourceType;
        this.description = description;
    }
}
