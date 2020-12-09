package ru.dpopkov.knowthenics.model;

public class Question {
    private Category category;
    private String wordingEn;
    private String wordingRu;
    private String shortAnswerEn;
    private String shortAnswerRu;
    private Answer preferredAnswer;
    private QuestionStat stat;
    private String comment;

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

    public QuestionStat getStat() {
        return stat;
    }

    public void setStat(QuestionStat stat) {
        this.stat = stat;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
