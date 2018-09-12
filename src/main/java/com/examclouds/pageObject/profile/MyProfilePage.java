package com.examclouds.pageObject.profile;

import com.examclouds.model.Person;
import com.examclouds.pageObject.base.BasePage;
import io.ddavison.conductor.Locomotive;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

/**
 * Created by Tatyana on 26.05.2016.
 */
public class MyProfilePage extends BasePage {

    public static final String TITLE = "My Profile on ExamClouds";

    public static final String LOC_INPUT_LOGIN = "input[name$='login']";
    public static final String LOC_INPUT_FIRST_NAME = "input[name$='firstName']";
    public static final String LOC_INPUT_LAST_NAME = "input[name$='lastName']";
    public static final String LOC_INPUT_EMAIL = "input[name$='email']";
    public static final By LOC_SAVE_BTN = By.xpath("//input[@name='Save']");
    public static final String LOC_CHANGE_PSWD_BTN = "input[name$='ChangePassword']";

    public MyProfilePage(Locomotive baseTest) {
        super(baseTest);
    }

    @Override
    public String getTitle() {
        return TITLE;
    }

    public MyProfilePage verifyPersonData(Person person) {
        test.validateText(LOC_INPUT_LOGIN, person.getLogin())
                .validateText(LOC_INPUT_FIRST_NAME, person.getFirstName())
                .validateText(LOC_INPUT_LAST_NAME, person.getLastName())
                .validateText(LOC_INPUT_EMAIL, person.getEmail());
        return this;
    }

    public MyProfilePage setPersonData(Person person) {
        test.setText(LOC_INPUT_LOGIN, person.getLogin())
                .setText(LOC_INPUT_FIRST_NAME, person.getFirstName())
                .setText(LOC_INPUT_LAST_NAME, person.getLastName())
                .setText(LOC_INPUT_EMAIL, person.getEmail());
        return this;
    }

    public MyProfilePage submitPersonData() {
        test.click(LOC_SAVE_BTN);
        return this;
    }

    public ChangePasswordPage openChangePasswordWindow() {
        test.click(LOC_CHANGE_PSWD_BTN);
        return new ChangePasswordPage(test);
    }

    public MyProfilePage acceptSubmitData() {
        waitForAlert();
        Alert alert = test.driver.switchTo().alert();
        alert.accept();
        return this;
    }


}
