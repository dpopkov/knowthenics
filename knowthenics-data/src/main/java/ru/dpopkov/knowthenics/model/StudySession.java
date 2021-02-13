package ru.dpopkov.knowthenics.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * Создается во время начала каждого сеанса работы с Deck.
 * Содержит последовательность Карточек.
 * Помнит индекс текущей карточки.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "study_sessions")
public class StudySession extends BaseEntity {

    private LocalDateTime started;
    private LocalDateTime finished;

    @ManyToMany
    private List<FlashCard> flashCards = new LinkedList<>();

    private int currentCardIndex;

    public void addCard(FlashCard card) {
        flashCards.add(card);
    }

    public void addAllCards(Collection<FlashCard> collection) {
        flashCards.addAll(collection);
    }

    public FlashCard getCurrentCard() {
        return flashCards.get(currentCardIndex);
    }

    public boolean isAtEnd() {
        return currentCardIndex == flashCards.size() - 1;
    }

    public boolean isFinished() {
        return finished != null;
    }

    public void moveToNext() {
        currentCardIndex++;
    }
}
