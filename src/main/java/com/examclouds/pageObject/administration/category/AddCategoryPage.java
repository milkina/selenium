package com.examclouds.pageObject.administration.category;

import com.examclouds.pageObject.base.BasePage;
import com.examclouds.pageObject.base.MessagePage;
import io.ddavison.conductor.Locomotive;
import org.openqa.selenium.By;

/**
 * Created by Tatyana on 12.12.2016.
 */
public class AddCategoryPage extends BasePage {
    public static final String LOC_TEST_SELECT = "#TEST_PATH";

    public static final String LOC_CATEGORY_SELECT = "#CATEGORY_PATH";
    private static final String LOC_ADD_CATEGORY_BTN = "input[name='Add']";

    public AddCategoryPage(Locomotive baseTest) {
        super(baseTest);
    }

    @Override
    public String getTitle() {
        return "Add Category";
    }

    public AddCategoryPage selectTest(String testPath) {
        test.selectOptionByValue(LOC_TEST_SELECT, testPath);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        return this;
    }

    public AddCategoryPage selectCategory(String categoryPath) {
        test.selectOptionByValue(LOC_CATEGORY_SELECT, categoryPath);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        return this;
    }

    public MessagePage clickAdd() {
        test.click(LOC_ADD_CATEGORY_BTN);
        return new MessagePage(test);
    }
}
