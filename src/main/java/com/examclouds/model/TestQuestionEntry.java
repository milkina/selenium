package com.examclouds.model;

import java.util.List;

public class TestQuestionEntry extends AbstractQuestionEntry {
    private List<Answer> answers;

    public TestQuestionEntry(String question, List<Answer> answers) {
        super(question);
        this.answers = answers;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public String getAnswer() {
        return answers.stream().findFirst().get().getText();
    }
}
