package com.examclouds.pageObject.test;

import com.examclouds.pageObject.base.BasePage;
import com.examclouds.pageObject.exam.StartPageTest;
import io.ddavison.conductor.Locomotive;

public class TestsPage extends BasePage {
    public static final String LOC_COURSE_LNK = "a[id='%s']";

    private String title = "Tests";

    public TestsPage(Locomotive baseTest) {
        super(baseTest);
    }

    @Override
    public String getTitle() {
        return title;
    }

    public StartPageTest openStartPageTest(String testName) {
        test.click(String.format(LOC_COURSE_LNK, testName));
        return new StartPageTest(test, testName);
    }

    public TestsPage validateCoursePresent(String courseName) {
        test.validateTextPresent(courseName);
        return this;
    }

}

