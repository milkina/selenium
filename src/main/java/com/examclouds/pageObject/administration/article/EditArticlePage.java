package com.examclouds.pageObject.administration.article;

import com.examclouds.pageObject.base.BasePage;
import io.ddavison.conductor.Locomotive;

/**
 * Created by Tatyana on 29.06.2016.
 */
public class EditArticlePage extends BasePage {
    public static final String LOC_URL_INPUT = "input[name$='URL_PARAM']";
    public static final String LOC_IMAGE_INPUT = "input[name$='ARTICLE_IMAGE']";
    public static final String LOC_TITLE_INPUT = "input[name$='TITLE']";
    public static final String LOC_SAVE_BTN = "input[name$='Save']";
    public static final String LOC_KEYWORDS_INPUT = "textarea[name='keywords']";
    public static final String LOC_DESCRIPTION_INPUT = "textarea[name='description']";
    public EditArticlePage(Locomotive baseTest) {
        super(baseTest);
    }

    @Override
    public String getTitle() {
        return "Edit Article";
    }

    public EditArticlePage setUrl(String value){
        test.setText(LOC_URL_INPUT, value);
        return this;
    }

    public EditArticlePage setImage(String value){
        test.setText(LOC_IMAGE_INPUT, value);
        return this;
    }

    public EditArticlePage setDescription(String value){
        test.setText(LOC_DESCRIPTION_INPUT, value);
        return this;
    }

    public EditArticlePage setKeywords(String value){
        test.setText(LOC_KEYWORDS_INPUT, value);
        return this;
    }

    public EditArticlePage setTitle(String value){
        test.setText(LOC_TITLE_INPUT, value);
        return this;
    }

    public EditArticlePage clickSaveBtn(){
        test.click(LOC_SAVE_BTN);
        return this;
    }

    public EditArticlePage validateUrl(String url){
        test.validateText(LOC_URL_INPUT, url);
        return this;
    }

    public EditArticlePage validateImage(String image){
        test.validateText(LOC_IMAGE_INPUT,image);
        return this;
    }

    public EditArticlePage validateTitle(String title){
        test.validateText(LOC_TITLE_INPUT,title);
        return this;
    }

    public EditArticlePage validateKeywords(String value){
        test.validateText(LOC_KEYWORDS_INPUT,value);
        return this;
    }

    public EditArticlePage validateDescription(String value){
        test.validateText(LOC_DESCRIPTION_INPUT,value);
        return this;
    }
}
