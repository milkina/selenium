package com.examclouds.pageObject.administration.question;

import com.examclouds.pageObject.base.BasePage;
import com.examclouds.pageObject.base.MessagePage;
import io.ddavison.conductor.Locomotive;

/**
 * Created by Tatyana on 11.02.2017.
 */
public class MoveQuestionsPage extends BasePage {
    public static final String LOC_SAVE_BTN = "input[name$='Save']";
    public static final String LOC_FROM_INPUT = "input[name$='FROM_NUMBER']";
    public static final String LOC_TO_INPUT = "input[name$='TO_NUMBER']";
    public static final String LOC_TEST_SELECT = "#TEST_PATH";
    public static final String LOC_CATEGORY_SELECT = "#CATEGORY_PATH";

    public MoveQuestionsPage(Locomotive baseTest) {
        super(baseTest);
    }

    @Override
    public String getTitle() {
        return "Move Questions";
    }

    public MoveQuestionsPage validateTestNamePresent(String testName) {
        test.validateTextPresent(testName);
        return this;
    }

    public MoveQuestionsPage validateCategoryNamePresent(String categoryName) {
        test.validateTextPresent(categoryName);
        return this;
    }

    public MoveQuestionsPage validateMessagePresent(String message) {
        test.validateTextPresent(message);
        return this;
    }

    public MoveQuestionsPage selectTest(String testPath) {
        test.selectOptionByValue(LOC_TEST_SELECT, testPath);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        return this;
    }

    public MoveQuestionsPage selectCategory(String categoryPath) {
        test.selectOptionByValue(LOC_CATEGORY_SELECT, categoryPath);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        return this;
    }

    public MoveQuestionsPage setFromNumber(String value) {
        test.setText(LOC_FROM_INPUT, value);
        return this;
    }

    public MoveQuestionsPage setToNumber(String value) {
        test.setText(LOC_TO_INPUT, value);
        return this;
    }

    public MessagePage successfulSaveData() {
        test.click(LOC_SAVE_BTN);
        return new MessagePage(test);
    }

    public MoveQuestionsPage saveData() {
        test.click(LOC_SAVE_BTN);
        return this;
    }
}
