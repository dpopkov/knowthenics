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
@Table(name = "answers")
public class Answer extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    @Column(name = "wording_en")
    @Lob
    private String wordingEn;

    @Column(name = "wording_ru")
    @Lob
    private String wordingRu;

    @Column(name = "answer_type")
    @Enumerated(EnumType.STRING)
    private AnswerType answerType;

    @ManyToOne
    @JoinColumn(name = "source_id")
    private Source source;

    @Column(name = "source_details")
    private String sourceDetails;

    @Column(name = "comment")
    private String comment;

    @ManyToMany
    @JoinTable(name = "answer_key_term",
            joinColumns = @JoinColumn(name = "answer_id"), inverseJoinColumns = @JoinColumn(name = "key_term_id"))
    private Set<KeyTerm> keyTerms = new HashSet<>();

    @Builder
    public Answer(Long id, Question question, String wordingEn, String wordingRu, AnswerType answerType, Source source,
                  String sourceDetails, String comment, Set<KeyTerm> keyTerms) {
        super(id);
        this.question = question;
        this.wordingEn = wordingEn;
        this.wordingRu = wordingRu;
        this.answerType = answerType;
        this.source = source;
        this.sourceDetails = sourceDetails;
        this.comment = comment;
        this.keyTerms = keyTerms;
    }

    public void addKeyTerm(KeyTerm keyTerm) {
        if (keyTerms == null) {
            keyTerms = new HashSet<>();
        }
        keyTerms.add(keyTerm);
        keyTerm.getAnswers().add(this);
    }
}
