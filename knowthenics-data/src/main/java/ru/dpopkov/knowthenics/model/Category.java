package ru.dpopkov.knowthenics.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "categories")
public class Category extends BaseEntity {
    @NotBlank
    @Size(min = 3, max = 255)
    private String name;
    @Size(max = 255)
    private String description;

    @Builder
    public Category(Long id, String name, String description) {
        super(id);
        this.name = name;
        this.description = description;
    }

    @Override
    public String toString() {
        return name;
    }
}
