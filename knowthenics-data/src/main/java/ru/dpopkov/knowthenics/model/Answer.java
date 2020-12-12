package ru.dpopkov.knowthenics.model;

public class Answer extends BaseEntity {
    private Question question;
    private String wordingEn;
    private String wordingRu;
    private String answerType;
    private Source source;
    private String comment;

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

    public String getAnswerType() {
        return answerType;
    }

    public void setAnswerType(String answerType) {
        this.answerType = answerType;
    }

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
