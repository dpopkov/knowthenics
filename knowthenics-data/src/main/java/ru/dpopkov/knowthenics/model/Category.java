package ru.dpopkov.knowthenics.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "categories")
public class Category extends BaseEntity {
    private String name;
    private String description;

    @Builder
    public Category(Long id, String name, String description) {
        super(id);
        this.name = name;
        this.description = description;
    }
}
