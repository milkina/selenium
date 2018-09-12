package com.examclouds.model;

/**
 * Created by Tatyana on 08.12.2016.
 */
public class QuestionEntry {
    private String question;
    private String answer;

    public QuestionEntry(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
