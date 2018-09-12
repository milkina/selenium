package com.examclouds.pageObject.home;

import com.examclouds.pageObject.base.BasePage;
import io.ddavison.conductor.Locomotive;
import org.openqa.selenium.By;

/**
 * Created by Tatyana on 25.05.2016.
 */
public class LoginPage extends BasePage {


    //input
    public static final String LOC_INPUT_LOGIN = "input[name$='login']";
    public static final String LOC_INPUT_PASSWORD = "input[name$='password']";

    public static final By LOC_BTN_ENTER = By.xpath("//input[@name='Enter']");
    public LoginPage(Locomotive baseTest) {
        super(baseTest);
    }
    @Override
    public String getTitle() {
        return  "Login to ExamClouds";
    }

    public BasePage login(String user, String password) {
        test.setText(LOC_INPUT_LOGIN, user)
                .setText(LOC_INPUT_PASSWORD, password)
                .click(LOC_BTN_ENTER);
        return this;
    }

    public BasePage sysadminLogin() {
        return login(SYSADMIN_LOGIN, SYSADMIN_PASSWORD);
    }

    public BasePage userLogin() {
        return login(USER_LOGIN, USER_PASSWORD);
    }

    public BasePage wrongUserLogin() {
        return login(WRONG_USER_LOGIN, USER_PASSWORD);
    }

    public BasePage wrongUserPassword() {
        return login(USER_LOGIN, WRONG_USER_PASSWORD);
    }

    public BasePage enterButtonExist() {
        test.validatePresent(LOC_BTN_ENTER);
        return this;
    }
}
