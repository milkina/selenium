package com.examclouds.pageObject.exam;

import com.examclouds.pageObject.base.BasePage;
import com.examclouds.pageObject.category.CategoryPage;
import io.ddavison.conductor.Locomotive;
import org.openqa.selenium.By;

public class StartQuestionTestPage extends BasePage {
    public static final By LOC_START_QUIZ_BTN = By.xpath("//input[@id='startQuiz']");
    public static final String LOC_SELECT_OPTIONS_DIV = "div[id$='selectOptionsPanel']";
    public static final String LOC_EXAM_OPTION = "input[value='%s']";
    private String title;

    public StartQuestionTestPage(Locomotive baseTest, String title) {
        super(baseTest);
        this.title = title;
    }

    @Override
    public String getTitle() {
        return title;
    }

    public ShowExamQuestionPage clickStartQuizButton() {
        test.click(LOC_START_QUIZ_BTN);
        return new ShowExamQuestionPage(test, title);
    }

    public StartQuestionTestPage validateOptionsPresent() {
        test.validatePresent(LOC_SELECT_OPTIONS_DIV);
        return this;
    }

    public StartQuestionTestPage selectOption(String title) {
        test.click(String.format(LOC_EXAM_OPTION, title));
        return this;
    }


    public StartQuestionTestPage validateOptionsNotPresent() {
        test.validateNotPresent(LOC_SELECT_OPTIONS_DIV);
        return this;
    }
}
