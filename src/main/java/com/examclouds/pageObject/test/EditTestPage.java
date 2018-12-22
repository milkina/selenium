package com.examclouds.pageObject.test;

import com.examclouds.model.Test;
import io.ddavison.conductor.Locomotive;

/**
 * Created by Tatyana on 21.01.2017.
 */
public class EditTestPage extends ChangeTestPage {

    public EditTestPage(Locomotive baseTest) {
        super(baseTest);
    }

    @Override
    public String getTitle() {
        return "Edit Test";
    }

    public EditTestPage validateTestName(String testName) {
        test.validateText(LOC_TEST_NAME_INPUT, testName);
        return this;
    }

    public EditTestPage validateTestPathName(String testPathName) {
        test.validateText(LOC_TEST_PATHNAME_INPUT, testPathName);
        return this;
    }

    public EditTestPage validateTestTags(String testTags) {
        test.validateText(LOC_TEST_TAGS_INPUT, testTags);
        return this;
    }

    public EditTestPage validateTestImg(String value) {
        test.validateText(LOC_IMG_INPUT, value);
        return this;
    }

    public EditTestPage validateTestKeywords(String value) {
        test.validateText(LOC_TEST_KEYWORDS_TEXTAREA, value);
        return this;
    }

    public EditTestPage validateTestDescription(String value) {
        test.validateText(LOC_DESCRIPTION_INPUT, value);
        return this;
    }

    public EditTestPage validateTestTitle(String value) {
        test.validateText(LOC_TITLE_INPUT, value);
        return this;
    }

    public EditTestPage validateTestLanguage(String value) {
        test.validateText(LOC_LANGUAGE_SELECT, value);
        return this;
    }

    public EditTestPage validateData(Test test) {
        validateTestName(test.getName());
        validateTestPathName(test.getPathName());
        validateTestTags(test.getTags());
        validateTestImg(test.getArticle().getImgUrl());
        validateTestKeywords(test.getArticle().getKeywords());
        validateTestDescription(test.getArticle().getDescription());
        validateTestTitle(test.getArticle().getTitle());
        validateTestLanguage(test.getLanguageCode());
        return this;
    }
}
