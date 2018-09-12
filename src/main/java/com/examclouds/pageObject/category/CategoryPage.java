package com.examclouds.pageObject.category;

import com.examclouds.pageObject.base.BasePage;
import com.examclouds.pageObject.exam.ShowExamQuestionPage;
import com.examclouds.pageObject.test.TestPage;
import io.ddavison.conductor.Locomotive;
import org.openqa.selenium.By;

/**
 * Created by Tatyana on 29.05.2016.
 */
public class CategoryPage extends TestPage {
    public static final By LOC_START_QUIZ_BTN = By.xpath("//input[@id='startQuiz']");
    public static final String LOC_SELECT_OPTIONS_DIV = "div[id$='selectOptionsPanel']";
    public static final String LOC_EXAM_OPTION = "input[value='%s']";
    public static final String CATEGORY_TITLE = "Basic Concepts - Web Services";
    public static final String CATEGORY_NAME = "Basic Concepts";
    public static final String CATEGORY_PATHNAME = "basic-concepts";
    public static final String TEST_NAME = "Web Services";
    private String title;

    public CategoryPage(Locomotive baseTest, String title) {
        super(baseTest, title);
        this.title = title;
    }

    @Override
    public String getTitle() {
        return title;
    }

    public CategoryPage validateStartQuizButtonPresent() {
        test.validatePresent(LOC_START_QUIZ_BTN);
        return this;
    }

    public ShowExamQuestionPage clickStartQuizButton() {
        test.click(LOC_START_QUIZ_BTN);
        return new ShowExamQuestionPage(test, title);
    }

    public CategoryPage validateOptionsNotPresent() {
        test.validateNotPresent(LOC_SELECT_OPTIONS_DIV);
        return this;
    }

    public CategoryPage validateOptionsPresent() {
        test.validatePresent(LOC_SELECT_OPTIONS_DIV);
        return this;
    }

    public CategoryPage selectOption(String title) {
        test.click(String.format(LOC_EXAM_OPTION, title));
        return this;
    }

}
