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

    public void addKeyTerm(KeyTerm keyTerm) {
        keyTerms.add(keyTerm);
        keyTerm.getQuestions().add(this);
    }
}
