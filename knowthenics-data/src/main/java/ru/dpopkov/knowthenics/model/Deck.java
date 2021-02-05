package ru.dpopkov.knowthenics.model;

import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "deck")
public class Deck extends BaseEntity {
    @NotBlank
    @Size(min = 3, max = 255)
    private String name;
    @Size(max = 255)
    private String description;

    @OneToMany(
            mappedBy = "deck",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<FlashCard> flashCards = new HashSet<>();

    public Deck(@NotBlank @Size(min = 3, max = 255) String name) {
        this.name = name;
    }

    @Builder
    public Deck(Long id, @NotBlank @Size(min = 3, max = 255) String name, @Size(max = 255) String description,
                Set<FlashCard> flashCards) {
        super(id);
        this.name = name;
        this.description = description;
        this.flashCards = flashCards != null ? flashCards : new HashSet<>();
    }

    public void addCard(FlashCard card) {
        flashCards.add(card);
        card.setDeck(this);
    }

    public void removeCard(FlashCard card) {
        flashCards.remove(card);
        card.setDeck(null);
    }

    public int size() {
        return flashCards.size();
    }
}
