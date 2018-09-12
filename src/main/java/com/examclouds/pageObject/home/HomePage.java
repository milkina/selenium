package com.examclouds.pageObject.home;

import com.examclouds.pageObject.base.BasePage;
import com.examclouds.pageObject.test.TestPage;
import io.ddavison.conductor.Locomotive;

/**
 * Created by Tatyana on 19.05.2016.
 */
public class HomePage extends BasePage {

    public HomePage(Locomotive baseTest) {
        super(baseTest);
    }

    @Override
    public String getTitle() {
        return  "Free Oracle Java Certification Tutorial on ExamClouds";
    }


}
