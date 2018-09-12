package com.examclouds.pageObject.administration.category;

import com.examclouds.model.Category;
import io.ddavison.conductor.Locomotive;

/**
 * Created by Tatyana on 20.06.2016.
 */
public class EditCategoryPage extends CategoryPage {
    public static final String LOC_CATEGORY_ID_SPAN = "span[name='categoryId']";

    public EditCategoryPage(Locomotive baseTest) {
        super(baseTest);
    }

    @Override
    public String getTitle() {
        return null;
    }

    public EditCategoryPage validateCategoryIdPresent() {
        test.validatePresent(LOC_CATEGORY_ID_SPAN);
        return this;
    }

    public EditCategoryPage validateCategoryName(String name) {
        test.validateText(LOC_CATEGORY_NAME_INPUT, name);
        return this;
    }

    public EditCategoryPage validateCategoryPathName(String pathName) {
        test.validateText(LOC_CATEGORY_PATHNAME_INPUT, pathName);
        return this;
    }

    public EditCategoryPage validateCategoryImage(String image) {
        if (image != null) {
            test.validateText(LOC_CATEGORY_IMAGE_INPUT, image);
        }
        return this;
    }

    public EditCategoryPage validateCategoryKeywords(String keywords) {
        test.validateText(LOC_CATEGORY_KEYWORDS_INPUT, keywords);
        return this;
    }

    public EditCategoryPage validateCategoryDescription(String description) {
        test.validateText(LOC_CATEGORY_DESCRIPTION_INPUT, description);
        return this;
    }

    public EditCategoryPage validateCategory(Category category) {
        validateCategoryIdPresent();
        validateCategoryName(category.getName());
        validateCategoryPathName(category.getPathName());
        validateCategoryImage(category.getImage());
        validateCategoryKeywords(category.getKeywords());
        validateCategoryDescription(category.getDescription());
        return this;
    }
}
