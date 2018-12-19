package com.examclouds.pageObject.administration.user;

import com.examclouds.pageObject.base.BasePage;
import io.ddavison.conductor.Locomotive;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UserHistoryPage extends BasePage {
    private String title;

    public UserHistoryPage(Locomotive baseTest, String title) {
        super(baseTest);
        this.title = title;
    }

    @Override
    public String getTitle() {
        return title;
    }

    public UserHistoryPage validateExamResultPresent(String categoryName) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd");
        test.validateTextPresent(simpleDateFormat.format(new Date()));
        test.validateTextPresent("100.0%");
        test.validateTextPresent(categoryName);
        return this;
    }
}
