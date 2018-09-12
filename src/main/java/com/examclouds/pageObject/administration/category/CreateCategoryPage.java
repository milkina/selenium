package com.examclouds.pageObject.administration.category;

import io.ddavison.conductor.Locomotive;

/**
 * Created by Tatyana on 18.06.2016.
 */
public class CreateCategoryPage extends CategoryPage {

    public CreateCategoryPage(Locomotive baseTest) {
        super(baseTest);
    }

    @Override
    public String getTitle() {
        return "Create Category on ExamClouds";
    }


}
