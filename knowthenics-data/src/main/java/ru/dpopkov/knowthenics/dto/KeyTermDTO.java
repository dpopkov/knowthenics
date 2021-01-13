package ru.dpopkov.knowthenics.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class KeyTermDTO extends BaseDTO {
    private String name;
    private String description;

    @Builder
    public KeyTermDTO(Long id, String name, String description) {
        super(id);
        this.name = name;
        this.description = description;
    }
}
