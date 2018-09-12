package com.examclouds.pageObject.article;

import com.examclouds.pageObject.base.BasePage;
import io.ddavison.conductor.Locomotive;

/**
 * Created by Tatyana on 26.07.2016.
 */
public class ArticleListPage extends BasePage {
    public ArticleListPage(Locomotive baseTest) {
        super(baseTest);
    }

    @Override
    public String getTitle() {
        return "Java Tutorial Articles";
    }
}
