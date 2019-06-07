package com.examclouds.pageObject.exam;

import com.examclouds.pageObject.test.TestPage;
import io.ddavison.conductor.Locomotive;

public class AbstractShowExamQuestionPage extends TestPage {
    public static final String LOC_NEXT_BTN = "a[class$='nextHref']";
    public static final String LOC_PREVIOUS_BTN = "a[class$='previousHref']";
    public static final String LOC_QUESTION_NUMBER_DIV = "div[id$='questionEntryNumber']";
    private String title;

    public AbstractShowExamQuestionPage(Locomotive baseTest, String title) {
        super(baseTest, title);
        this.title = title;
    }

    @Override
    public String getTitle() {
        return title;
    }

    public AbstractShowExamQuestionPage validatePage(int i, int number) {
                validateCommentSectionPresent();
        validateButtons(i , number)
        .validateQuestionNumberSpan(i  + "/" + number);
        return this;
    }

    public AbstractShowExamQuestionPage validateQuestionNumberSpan(String text) {
        test.validateText(LOC_QUESTION_NUMBER_DIV, text);
        return this;
    }


    public AbstractShowExamQuestionPage validateButtons(int currentNumber, int number) {
        return validateNextButton(currentNumber, number)
                .validatePreviousButton(currentNumber);
    }

    public AbstractShowExamQuestionPage validateNextButton(int currentNumber, int number) {
        if (currentNumber == number) {
            test.validateNotPresent(LOC_NEXT_BTN);
        } else {
            test.validatePresent(LOC_NEXT_BTN);
        }
        return this;
    }

    public AbstractShowExamQuestionPage validatePreviousButton(int currentNumber) {
        if (currentNumber == 1) {
            test.validateNotPresent(LOC_PREVIOUS_BTN);
        } else {
            test.validatePresent(LOC_PREVIOUS_BTN);
        }
        return this;
    }
}
