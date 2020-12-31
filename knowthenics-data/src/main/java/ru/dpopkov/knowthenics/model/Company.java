package ru.dpopkov.knowthenics.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Company extends BaseEntity {
    private String title;
    private String description;

    @Builder
    public Company(Long id, String title, String description) {
        super(id);
        this.title = title;
        this.description = description;
    }
}
