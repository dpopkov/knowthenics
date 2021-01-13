package ru.dpopkov.knowthenics.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO extends BaseDTO {
    private String name;
    private String description;

    @Builder
    public CategoryDTO(Long id, String name, String description) {
        super(id);
        this.name = name;
        this.description = description;
    }
}
