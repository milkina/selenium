package com.examclouds.pageObject.exam;

import com.examclouds.model.AbstractQuestionEntry;
import com.examclouds.pageObject.test.TestPage;
import io.ddavison.conductor.Locomotive;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.testng.Assert;

import java.util.StringTokenizer;

/**
 * Created by Tatyana on 30.05.2016.
 */
public class ShowExamQuestionPage extends AbstractShowExamQuestionPage {
    public static final String LOC_ANSWER_BTN = "input[id$='a1']";
    public static final String LOC_ANSWER_DIV = "div[id$='answer1']";
    public static final String LOC_CHECKBOX = "input[name='isAnswered']";
    public static final String ANSWER_READ_TEXT = "Read Answer";
    public static final String ANSWER_HIDE_TEXT = "Hide Answer";
    public static final String STYLE_ATTRIBUTE = "style";

    public ShowExamQuestionPage(Locomotive baseTest, String title) {
        super(baseTest, title);
    }

    public ShowExamQuestionPage validateAnswerButtonText(String text) {
        test.validateText(LOC_ANSWER_BTN, text);
        return this;
    }

    public ShowExamQuestionPage clickAnswerButton() {
        test.click(LOC_ANSWER_BTN);
        return this;
    }

    public ShowExamQuestionPage validateAnswerDivPresent() {
        test.validateAttribute(LOC_ANSWER_DIV, STYLE_ATTRIBUTE, "display: block");
        return this;
    }

    public ShowExamQuestionPage validateAnswerDivNotPresent() {
        test.validateAttribute(LOC_ANSWER_DIV, STYLE_ATTRIBUTE, "");
        return this;
    }

    public Integer getQuestionsNumber() {
        return getInteger(1);
    }

    private Integer getInteger(int i) {
        String number = test.getText(LOC_QUESTION_NUMBER_SPAN);
        String numbers[] = number.split("/");
        return Integer.valueOf(numbers[i]);
    }

    public ShowExamQuestionPage validateCheckBoxChecked() {
        test.validateChecked(LOC_CHECKBOX);
        test.waitForWindow(getTitle());
        return this;
    }

    public ShowExamQuestionPage clickNextButton() {
        test.click(LOC_NEXT_BTN);
        test.waitForWindow(getTitle());
        return this;
    }

    public ShowExamQuestionPage clickPreviousButton() {
        test.click(LOC_PREVIOUS_BTN);
        test.waitForWindow(getTitle());
        return this;
    }

    public ShowExamQuestionPage validatePage(int i, int number) {
        super.validatePage(i, number);
        validateAnswerButtonText(ANSWER_READ_TEXT)
                .validateAnswerDivNotPresent()
                .clickAnswerButton()
                .validateAnswerButtonText(ANSWER_HIDE_TEXT)
                .validateAnswerDivPresent();
        return this;
    }

    @Override
    public ShowExamQuestionPage waitForWindow(String title) {
        super.waitForWindow(title);
        return this;
    }

    public ShowExamQuestionPage clickCheckbox() {
        test.click(LOC_CHECKBOX);
        return this;
    }

    @Override
    public ShowExamQuestionPage verifyAlertText(String text) {
        super.verifyAlertText(text);
        return this;
    }
}
