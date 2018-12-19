package com.examclouds.pageObject.test;

import com.examclouds.pageObject.category.CategoryPage;
import com.examclouds.pageObject.base.BasePage;
import io.ddavison.conductor.Locomotive;

/**
 * Created by Tatyana on 22.05.2016.
 */
public class TestPage extends BasePage {
    public static final String LOC_CATEGORY_MENU_DIV = "div[id$='categoryMenuDiv']";
    public static final String LOC_COMMENT_DIV = "div[id$='commentDiv']";
    public static final String LOC_CATEGORY_MENU_ITEM = "div[id$='categoryMenuDiv'] div a[id='categoryItem%s']";
    private String title;

    public TestPage(Locomotive baseTest, String title) {
        super(baseTest);
        this.title = title;
    }

    @Override
    public String getTitle() {
        return title;
    }

    public CategoryPage openCategoryPage(String categoryName, String testName, String categoryPathName) {
        title = testName;
        test.click(String.format(LOC_CATEGORY_MENU_ITEM, categoryPathName));
        return new CategoryPage(test, title);
    }

    public TestPage validateCategoryItemNotPresent(String categoryPathName) {
        test.validateNotPresent(String.format(LOC_CATEGORY_MENU_ITEM, categoryPathName));
        return this;
    }

    public TestPage validateCategoryMenuPresent() {
        test.validatePresent(LOC_CATEGORY_MENU_DIV);
        return this;
    }

    public TestPage validateCommentSectionPresent() {
        test.validatePresent(LOC_COMMENT_DIV);
        return this;
    }
}
