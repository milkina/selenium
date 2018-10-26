package com.examclouds.model;

public abstract class AbstractQuestionEntry {
    private String question;

    public AbstractQuestionEntry(String question) {
        this.question = question;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public abstract String getAnswer();

}
