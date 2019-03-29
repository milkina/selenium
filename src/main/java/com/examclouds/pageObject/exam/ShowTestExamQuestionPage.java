package com.examclouds.pageObject.exam;

import io.ddavison.conductor.Locomotive;

public class ShowTestExamQuestionPage extends AbstractShowExamQuestionPage {
    public static final String LOC_ANSWER_BTN = "input[name$='answerBtn']";
    public static final String LOC_CHECKBOX = "input[name='checkbox%d']";
    public static final String LOC_FINISH_BTN = "input[name='finishBtn']";

    public ShowTestExamQuestionPage(Locomotive baseTest, String title) {
        super(baseTest, title);
    }

    public ShowTestExamQuestionPage clickAnswerBtn() {
        test.click(LOC_ANSWER_BTN);
        test.waitForWindow(getTitle());
        return this;
    }

    public ShowTestExamResultPage clickFinishBtn(String login) {
        test.click(LOC_FINISH_BTN);
//        test.waitForWindow(getTitle());
        return new ShowTestExamResultPage(test, login + " | Test Result");
    }

    public ShowTestExamQuestionPage clickCheckbox(int i) {
        test.click(String.format(LOC_CHECKBOX, i));
        return this;
    }
}
