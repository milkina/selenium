package com.examclouds.pageObject.exam;

import com.examclouds.pageObject.base.BasePage;
import io.ddavison.conductor.Locomotive;

public class ShowTestExamResultPage extends BasePage {
    private String title;

    public ShowTestExamResultPage(Locomotive baseTest, String title) {
        super(baseTest, title);
        this.title = title;
    }

    @Override
    public String getTitle() {
        return title;
    }

    public ShowTestExamResultPage validatePage() {
        test.validateTextPresent("Test is passed.");
        test.validateTextPresent("100%");
        test.validateTextPresent("answers are correct");
        return this;
    }
}
