package com.examclouds.pageObject.test;

import com.examclouds.model.Test;
import com.examclouds.pageObject.base.BasePage;
import io.ddavison.conductor.Locomotive;

/**
 * Created by Tatyana on 29.01.2017.
 */
public class ChangeTestPage extends BasePage {
    public static final String LOC_TEST_NAME_INPUT = "input[name='testName']";
    public static final String LOC_TEST_PATHNAME_INPUT = "input[name='TEST_PATH']";
    public static final String LOC_TEST_TAGS_INPUT = "input[name='TEST_TAGS']";
    public static final String LOC_TEST_KEYWORDS_TEXTAREA = "textarea[name='keywords']";
    public static final String LOC_DESCRIPTION_INPUT = "textarea[name='description']";
    public static final String LOC_TITLE_INPUT = "textarea[name='TITLE']";
    public static final String LOC_IMG_INPUT = "input[name='ARTICLE_IMAGE']";
    public static final String LOC_SAVE_BTN = "input[name='Save']";
    public static final String LOC_LANGUAGE_SELECT = "select[name='LANGUAGE']";

    public ChangeTestPage(Locomotive baseTest) {
        super(baseTest);
    }

    @Override
    public String getTitle() {
        return null;
    }

    public ChangeTestPage setTestPathName(String value) {
        test.setText(LOC_TEST_PATHNAME_INPUT, value);
        return this;
    }

    public ChangeTestPage setTestName(String value) {
        test.setText(LOC_TEST_NAME_INPUT, value);
        return this;
    }

    public ChangeTestPage setTestTags(String value) {
        test.setText(LOC_TEST_TAGS_INPUT, value);
        return this;
    }

    public ChangeTestPage setTestKeywords(String value) {
        test.setText(LOC_TEST_KEYWORDS_TEXTAREA, value);
        return this;
    }

    public ChangeTestPage setTestDescription(String value) {
        test.setText(LOC_DESCRIPTION_INPUT, value);
        return this;
    }

    public ChangeTestPage setTestTitle(String value) {
        test.setText(LOC_TITLE_INPUT, value);
        return this;
    }

    public ChangeTestPage setTestImgUrl(String value) {
        test.setText(LOC_IMG_INPUT, value);
        return this;
    }

    public ChangeTestPage setArticleText(String str) {
        setTextArea(str, "ARTICLE_TEXT");
        return this;
    }

    public ChangeTestPage setItomText(String str) {
        setTextArea(str, "ICON_TEXT");
        return this;
    }

    public ChangeTestPage setLanguage(String str) {
        test.selectOptionByValue(LOC_LANGUAGE_SELECT, str);
        return this;
    }

    public ChangeTestPage setTestData(Test test) {
        setTestName(test.getName());
        setTestPathName(test.getPathName());
        setTestTags(test.getTags());

        setTestKeywords(test.getArticle().getKeywords());
        setTestDescription(test.getArticle().getDescription());
        setTestTitle(test.getArticle().getTitle());
        setTestImgUrl(test.getArticle().getImgUrl());
        setArticleText(test.getArticle().getText());
        setItomText(test.getItomText());
        setLanguage(test.getLanguageCode());
        return this;
    }

    public ChangeTestPage clickSave() {
        test.click(LOC_SAVE_BTN);
        return this;
    }
}
