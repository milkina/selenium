package com.examclouds.model;

import java.util.Set;

public class TestQuestionEntry {
    private String question;
    private Set<Answer> answers;

    public TestQuestionEntry(String question, Set<Answer> answers) {
        this.question = question;
        this.answers = answers;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Set<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(Set<Answer> answers) {
        this.answers = answers;
    }
}
