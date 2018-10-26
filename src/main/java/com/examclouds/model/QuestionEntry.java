package com.examclouds.model;

/**
 * Created by Tatyana on 08.12.2016.
 */
public class QuestionEntry extends AbstractQuestionEntry {
    private String answer;

    public QuestionEntry(String question, String answer) {
        super(question);
        this.answer = answer;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
