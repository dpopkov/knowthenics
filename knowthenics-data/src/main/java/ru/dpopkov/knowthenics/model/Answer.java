package ru.dpopkov.knowthenics.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "answers")
public class Answer extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    @Column(name = "wording_en")
    private String wordingEn;

    @Column(name = "wording_ru")
    private String wordingRu;

    @Column(name = "answer_type")
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

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public String getWordingEn() {
        return wordingEn;
    }

    public void setWordingEn(String wordingEn) {
        this.wordingEn = wordingEn;
    }

    public String getWordingRu() {
        return wordingRu;
    }

    public void setWordingRu(String wordingRu) {
        this.wordingRu = wordingRu;
    }

    public AnswerType getAnswerType() {
        return answerType;
    }

    public void setAnswerType(AnswerType answerType) {
        this.answerType = answerType;
    }

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }

    public String getSourceDetails() {
        return sourceDetails;
    }

    public void setSourceDetails(String sourceDetails) {
        this.sourceDetails = sourceDetails;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Set<KeyTerm> getKeyTerms() {
        return keyTerms;
    }

    public void setKeyTerms(Set<KeyTerm> keyTerms) {
        this.keyTerms = keyTerms;
    }

    public void addKeyTerm(KeyTerm keyTerm) {
        keyTerms.add(keyTerm);
        keyTerm.getAnswers().add(this);
    }
}
