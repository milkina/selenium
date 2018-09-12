package com.examclouds.pageObject.base;

import io.ddavison.conductor.Locomotive;

/**
 * Created by Tatyana on 25.05.2016.
 */
public class MessagePage extends BasePage {
    public static final String LOC_WRONG_MESSAGE = "span[id$='message']";

    public MessagePage(Locomotive baseTest) {
        super(baseTest);
    }

    @Override
    public String getTitle() {
        return "ExamClouds";
    }

    public MessagePage isMessagePresent(String message) {
        test.validateText(LOC_WRONG_MESSAGE, message);
        return this;
    }
}
