package ru.dpopkov.knowthenics.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
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

    public String getShortAnswerEn() {
        return shortAnswerEn;
    }

    public void setShortAnswerEn(String shortAnswerEn) {
        this.shortAnswerEn = shortAnswerEn;
    }

    public String getShortAnswerRu() {
        return shortAnswerRu;
    }

    public void setShortAnswerRu(String shortAnswerRu) {
        this.shortAnswerRu = shortAnswerRu;
    }

    public Answer getPreferredAnswer() {
        return preferredAnswer;
    }

    public void setPreferredAnswer(Answer preferredAnswer) {
        this.preferredAnswer = preferredAnswer;
    }

    public Set<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(Set<Answer> answers) {
        this.answers = answers;
    }

    public QuestionDrillStat getStat() {
        return stat;
    }

    public void setStat(QuestionDrillStat stat) {
        this.stat = stat;
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
        keyTerm.getQuestions().add(this);
    }

    public int getInterviewUsageCount() {
        return interviewUsageCount;
    }

    public void setInterviewUsageCount(int interviewUsageCount) {
        this.interviewUsageCount = interviewUsageCount;
    }
}
