package ru.dpopkov.knowthenics.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
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

    @Column(name = "wording_en")
    private String wordingEn;

    @Column(name = "wording_ru")
    private String wordingRu;

    @Column(name = "short_answer_en")
    private String shortAnswerEn;

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
        this.answers = answers;
        this.stat = stat;
        this.comment = comment;
        this.keyTerms = keyTerms;
        this.interviewUsageCount = interviewUsageCount;
    }

    public void addKeyTerm(KeyTerm keyTerm) {
        if (keyTerms == null) {
            keyTerms = new HashSet<>();
        }
        keyTerms.add(keyTerm);
        keyTerm.getQuestions().add(this);
    }
}
