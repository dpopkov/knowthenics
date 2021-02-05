package ru.dpopkov.knowthenics.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.PositiveOrZero;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "flashcards")
public class FlashCard extends BaseEntity{

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;
    @PositiveOrZero
    private int level;

    @ManyToOne(fetch = FetchType.LAZY)
    private Deck deck;

    public FlashCard(Question question) {
        this.question = question;
    }

    @Builder
    public FlashCard(Long id, Question question, int level, Deck deck) {
        super(id);
        this.question = question;
        this.level = level;
        this.deck = deck;
    }

    public void levelUp() {
        level++;
    }

    public void levelDown() {
        level = Math.max(level - 1, 0);
    }

    public void levelReset() {
        level = 0;
    }
}
