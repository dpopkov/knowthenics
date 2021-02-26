package ru.dpopkov.knowthenics.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "questions")
public class Question extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @NotBlank
    @Size(min = 8, max = 255)
    @Column(name = "wording_en")
    private String wordingEn;

    @Size(max = 255)
    @Column(name = "wording_ru")
    private String wordingRu;

    @Size(max = 255)
    @Column(name = "short_answer_en")
    private String shortAnswerEn;

    @Size(max = 255)
    @Column(name = "short_answer_ru")
    private String shortAnswerRu;

    @OneToOne
    @JoinColumn(name = "preferred_answer_id", referencedColumnName = "id")
    private Answer preferredAnswer;

    @OneToMany(mappedBy = "question")
    private Set<Answer> answers = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "question_stat_id", referencedColumnName = "id")
    private QuestionDrillStat stat;

    @Size(max = 255)
    @Column(name = "comment")
    private String comment;

    @ManyToMany
    @JoinTable(name = "question_key_term",
            joinColumns = @JoinColumn(name = "question_id"), inverseJoinColumns = @JoinColumn(name = "key_term_id"))
    private Set<KeyTerm> keyTerms = new HashSet<>();

    @Column(name = "usage_count")
    private int interviewUsageCount;

    @Builder
    public Question(Long id, Category category, String wordingEn, String wordingRu, String shortAnswerEn,
                    String shortAnswerRu, Answer preferredAnswer, Set<Answer> answers, QuestionDrillStat stat,
                    String comment, Set<KeyTerm> keyTerms, int interviewUsageCount) {
        super(id);
        this.category = category;
        this.wordingEn = wordingEn;
        this.wordingRu = wordingRu;
        this.shortAnswerEn = shortAnswerEn;
        this.shortAnswerRu = shortAnswerRu;
        this.preferredAnswer = preferredAnswer;
        this.answers = answers != null ? answers : new HashSet<>();
        this.stat = stat;
        this.comment = comment;
        this.keyTerms = keyTerms != null ? keyTerms : new HashSet<>();
        this.interviewUsageCount = interviewUsageCount;
    }

    public void addKeyTerm(KeyTerm keyTerm) {
        keyTerms.add(keyTerm);
        keyTerm.getQuestions().add(this);
    }

    public void updateSimpleFieldsFrom(Question update) {
        setCategory(update.getCategory());
        setWordingEn(update.getWordingEn());
        setWordingRu(update.getWordingRu());
        setShortAnswerEn(update.getShortAnswerEn());
        setShortAnswerRu(update.getShortAnswerRu());
        setComment(update.getComment());
    }

    public boolean hasShortAnswerEn() {
        return hasStringField(shortAnswerEn);
    }

    public boolean hasShortAnswerRu() {
        return hasStringField(shortAnswerRu);
    }

    public boolean hasComment() {
        return hasStringField(comment);
    }

    private boolean hasStringField(String field) {
        return field != null && !field.isEmpty() && !field.equals("-");
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Question)) {
            return false;
        }
        if (!super.equals(other)) {
            return false;
        }
        Question question = (Question) other;
        return Objects.equals(category, question.category) &&
                Objects.equals(wordingEn, question.wordingEn) &&
                Objects.equals(wordingRu, question.wordingRu);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), category, wordingEn, wordingRu);
    }
}
