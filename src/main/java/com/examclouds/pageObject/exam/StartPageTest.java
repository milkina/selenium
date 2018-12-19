package com.examclouds.pageObject.exam;

import com.examclouds.pageObject.base.BasePage;
import io.ddavison.conductor.Locomotive;

public class StartPageTest extends BasePage {
    private String title;
    private static final String CATEGORY_SELECT = "#CATEGORY_PATH";
    private static final String LOC_START_TEST_BTN = "input[name='startTest']";

    public StartPageTest(Locomotive baseTest, String title) {
        super(baseTest);
        this.title = title;
    }

    @Override
    public String getTitle() {
        return title;
    }

    public StartPageTest selectTest(String categoryPath) {
        test.selectOptionByValue(CATEGORY_SELECT, categoryPath);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        return this;
    }

    public ShowTestExamQuestionPage clickStartTestBtn(String testName) {
        test.click(LOC_START_TEST_BTN);
        return new ShowTestExamQuestionPage(test, testName);
    }
}
