package com.examclouds.pageObject.administration.category;

import com.examclouds.model.Category;
import com.examclouds.pageObject.base.BasePage;
import io.ddavison.conductor.Locomotive;

/**
 * Created by Tatyana on 20.06.2016.
 */
abstract public class CategoryPage extends BasePage {
    public static final String LOC_CATEGORY_NAME_INPUT = "input[name='categoryName']";
    public static final String LOC_CATEGORY_PATHNAME_INPUT = "input[name='categoryPathName']";
    public static final String LOC_CATEGORY_IMAGE_INPUT = "input[name='ARTICLE_IMAGE']";
    public static final String LOC_CATEGORY_INDEX_INPUT = "input[name='index']";
    public static final String LOC_CATEGORY_KEYWORDS_INPUT = "textarea[name='keywords']";
    public static final String LOC_CATEGORY_DESCRIPTION_INPUT = "textarea[name='description']";
    public static final String LOC_CATEGORY_TEXT_INPUT = "ARTICLE_TEXT";
    public static final String LOC_SAVE_BTN = "input[name='Save']";

    public CategoryPage(Locomotive baseTest) {
        super(baseTest);
    }

    public CategoryPage setCategoryName(String value) {
        test.setText(LOC_CATEGORY_NAME_INPUT, value);
        return this;
    }

    public CategoryPage setCategoryPathName(String value) {
        test.setText(LOC_CATEGORY_PATHNAME_INPUT, value);
        return this;
    }

    public CategoryPage setCategoryImage(String value) {
        test.setText(LOC_CATEGORY_IMAGE_INPUT, value);
        return this;
    }

    public CategoryPage setCategoryKeywords(String value) {
        test.setText(LOC_CATEGORY_KEYWORDS_INPUT, value);
        return this;
    }

    public CategoryPage setCategoryDescription(String value) {
        test.setText(LOC_CATEGORY_DESCRIPTION_INPUT, value);
        return this;
    }

    public CategoryPage setCategoryText(String value) {
        setTextArea(value, LOC_CATEGORY_TEXT_INPUT);
        return this;
    }


    public CategoryPage setCategoryData(Category category) {
        setCategoryName(category.getName());
        setCategoryPathName(category.getPathName());
        if (category.getImage() != null) {
            setCategoryImage(category.getImage());
        }
        if (category.getKeywords() != null) {
            setCategoryKeywords(category.getKeywords());
        }
        if (category.getDescription() != null) {
            setCategoryDescription(category.getDescription());
        }
        if (category.getText() != null) {
            setCategoryText(category.getText());
        }
        return this;
    }

    public CategoryPage clickSave() {
        test.click(LOC_SAVE_BTN);
        return this;
    }
}
