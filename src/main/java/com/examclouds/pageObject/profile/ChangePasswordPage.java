package com.examclouds.pageObject.profile;

import com.examclouds.pageObject.base.BasePage;
import io.ddavison.conductor.Locomotive;
import org.openqa.selenium.Alert;
import org.testng.Assert;

/**
 * Created by Tatyana on 27.05.2016.
 */
public class ChangePasswordPage extends BasePage {
    public static final String LOC_SPAN_LOGIN = "span[id='login']";
    public static final String LOC_INPUT_PASSWORD = "input[id='password']";
    public static final String LOC_INPUT_CONF_PASSWORD = "input[id='confirmPassword']";
    public static final String LOC_CONF_BTN = "input[id='confirm']";

    public ChangePasswordPage(Locomotive baseTest) {
        super(baseTest);
    }

    @Override
    public String getTitle() {
        return "Change Password";
    }

    public ChangePasswordPage verifyLoginText(String login) {
        test.validateText(LOC_SPAN_LOGIN, login);
        return this;
    }

    public ChangePasswordPage setPassword(String password) {
        test.setText(LOC_INPUT_PASSWORD, password);
        return this;
    }

    public ChangePasswordPage setConfPassword(String password) {
        test.setText(LOC_INPUT_CONF_PASSWORD, password);
        return this;
    }

    public ChangePasswordPage clickConfirm() {
        test.click(LOC_CONF_BTN);
        return this;
    }

    public MyProfilePage goToMyProfile() {
        return new MyProfilePage(test);
    }

    @Override
    public ChangePasswordPage verifyAlertText(String text) {
        super.verifyAlertText(text);
        return this;
    }

    public ChangePasswordPage validateTextPresent(String text) {
        test.validateTextPresent(text);
        return this;
    }
}
