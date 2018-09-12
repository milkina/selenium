package com.examclouds.pageObject.exam;

import com.examclouds.pageObject.test.TestPage;
import io.ddavison.conductor.Locomotive;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.testng.Assert;

import java.util.StringTokenizer;

/**
 * Created by Tatyana on 30.05.2016.
 */
public class ShowExamQuestionPage extends TestPage {
    public static final String LOC_ANSWER_BTN = "input[id$='a1']";
    public static final By LOC_NEXT_BTN = By.xpath("//input[@name='NEXT']");
    public static final String LOC_PREVIOUS_BTN = "input[name$='PREVIOUS']";
    public static final String LOC_ANSWER_DIV = "div[id$='answer1']";
    public static final String LOC_QUESTION_NUMBER_SPAN = "span[id$='questionEntryNumber']";
    public static final String LOC_CHECKBOX = "input[name='isAnswered']";

    public static final String ANSWER_READ_TEXT = "Read Answer";
    public static final String ANSWER_HIDE_TEXT = "Hide Answer";
    public static final String STYLE_ATTRIBUTE = "style";
    private String title;

    public ShowExamQuestionPage(Locomotive baseTest, String title) {
        super(baseTest, title);
        this.title = title;
    }

    @Override
    public String getTitle() {
        return title;
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

    public ShowExamQuestionPage validateQuestionNumberSpan(String text) {
        test.validateText(LOC_QUESTION_NUMBER_SPAN, text);
        return this;
    }

    public ShowExamQuestionPage validateButtons(int currentNumber, int number) {
        return validateNextButton(currentNumber, number)
                .validatePreviousButton(currentNumber);
    }

    public ShowExamQuestionPage validateNextButton(int currentNumber, int number) {
        if (currentNumber == number) {
            test.validateNotPresent(LOC_NEXT_BTN);
        } else {
            test.validatePresent(LOC_NEXT_BTN);
        }
        return this;
    }

    public ShowExamQuestionPage validatePreviousButton(int currentNumber) {
        if (currentNumber == 1) {
            test.validateNotPresent(LOC_PREVIOUS_BTN);
        } else {
            test.validatePresent(LOC_PREVIOUS_BTN);
        }
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
        validateCategoryMenuPresent()
                .validateCommentSectionPresent();
        validateButtons(i, number)
                .validateQuestionNumberSpan(i + "/" + number)
                .validateAnswerButtonText(ANSWER_READ_TEXT)
                .validateAnswerDivNotPresent()
                .clickAnswerButton()
                .validateAnswerButtonText(ANSWER_HIDE_TEXT)
                .validateAnswerDivPresent();
        return this;
    }

    public ShowExamQuestionPage clickCheckbox() {
        test.click(LOC_CHECKBOX);
        return this;
    }

    @Override
    public ShowExamQuestionPage waitForWindow(String title) {
        super.waitForWindow(title);
        return this;
    }

    @Override
    public ShowExamQuestionPage verifyAlertText(String text) {
        super.verifyAlertText(text);
        return this;
    }
}
