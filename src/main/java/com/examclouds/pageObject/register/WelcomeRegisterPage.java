package com.examclouds.pageObject.register;

import com.examclouds.pageObject.base.BasePage;
import io.ddavison.conductor.Locomotive;

/**
 * Created by Tatyana on 25.05.2016.
 */
public class WelcomeRegisterPage  extends BasePage {
    public static final String TITLE ="Welcome on ExamClouds";
    public static final String LOC_SPAN_MSG= "span[id$='welcomeText']";
    public static final String WELCOME_TEXT ="%s, thanks for registering!\n" +
            "Registered users are able to post comments and pass quizzes.";

    public WelcomeRegisterPage(Locomotive baseTest) {
        super(baseTest);


    }

    @Override
    public String getTitle() {
        return TITLE;
    }

    public WelcomeRegisterPage verifyWelcomeText(String userName){
         test.validateText(LOC_SPAN_MSG,String.format(WELCOME_TEXT,userName));
        return this;
    }
}
