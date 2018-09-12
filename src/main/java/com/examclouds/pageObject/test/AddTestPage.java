package com.examclouds.pageObject.test;

import io.ddavison.conductor.Locomotive;

/**
 * Created by Tatyana on 19.01.2017.
 */
public class AddTestPage extends ChangeTestPage {
    public AddTestPage(Locomotive baseTest) {
        super(baseTest);
    }

    @Override
    public String getTitle() {
        return "Add Test";
    }
}
