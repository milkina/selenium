package com.examclouds.pageObject.article;

import com.examclouds.pageObject.base.BasePage;
import io.ddavison.conductor.Locomotive;

/**
 * Created by Tatyana on 25.07.2016.
 */
public class ArticlePage extends BasePage {
    private String title;

    public ArticlePage(Locomotive baseTest, String title) {
        super(baseTest);
        this.title = title;
    }

    @Override
    public String getTitle() {
        return title;
    }

    public ArticlePage validateTextPresent(String text) {
        test.validateTextPresent(text);
        return this;
    }

}
