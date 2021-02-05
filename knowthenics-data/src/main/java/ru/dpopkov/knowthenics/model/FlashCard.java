package ru.dpopkov.knowthenics.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.PositiveOrZero;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "flashcards")
public class FlashCard extends BaseEntity{

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;              // todo: FlashCard must contain only text !!!!!
    @PositiveOrZero
    private int level;

    @ManyToOne(fetch = FetchType.LAZY)
    private Deck deck;

    private String questionWording;
    private String answerWording;

    public FlashCard(Question question) {
        this.question = question;
        initWording();
    }

    @Builder
    public FlashCard(Long id, Question question, @PositiveOrZero int level, Deck deck,
                     String questionWording, String answerWording) {
        super(id);
        this.question = question;
        this.level = level;
        this.deck = deck;
        this.questionWording = questionWording;
        this.answerWording = answerWording;
        initWording();
    }

    private void initWording() {
        if (question != null) {
            questionWording = joinWording(question.getWordingEn(), question.getWordingRu());
            Answer answer = question.getPreferredAnswer();
            if (answer != null) {
                answerWording = joinWording(answer.getWordingEn(), answer.getWordingRu());
            }
        }
    }

    private String joinWording(String en, String ru) {
        StringBuilder sb = new StringBuilder();
        if (en != null && !en.isEmpty()) {
            sb.append(en);
            sb.append(System.lineSeparator());
        }
        if (ru != null && !ru.isEmpty()) {
            sb.append(ru);
        }
        return sb.toString();
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FlashCard)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        FlashCard flashCard = (FlashCard) o;
        return level == flashCard.level
                && Objects.equals(questionWording, flashCard.questionWording)
                && Objects.equals(answerWording, flashCard.answerWording);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), level, questionWording, answerWording);
    }
}
