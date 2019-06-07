package com.examclouds.pageObject.profile;

import com.examclouds.model.Person;
import com.examclouds.pageObject.administration.ShowQuestionsPage;
import com.examclouds.pageObject.administration.question.AddQuestionPage;
import com.examclouds.pageObject.base.BasePage;
import io.ddavison.conductor.Locomotive;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;

import java.text.SimpleDateFormat;
import java.util.Date;

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
    public static final String LOC_ADD_QUESTION_HRF = "a[id='addQuestion']";
    public static final String LOC_SEE_MY_QUESTIONS_HREF = "a[id='myQuestions']";

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

    public AddQuestionPage addQuestion() {
        test.click(LOC_ADD_QUESTION_HRF);
        return new AddQuestionPage(test);
    }

    public ChangePasswordPage openChangePasswordWindow() {
        test.click(LOC_CHANGE_PSWD_BTN);
        return new ChangePasswordPage(test);
    }


    public ShowQuestionsPage clickMyQuestions() {
        test.click(LOC_SEE_MY_QUESTIONS_HREF);
        return new ShowQuestionsPage(test, "Questions -");
    }

    public MyProfilePage acceptSubmitData() {
        waitForAlert();
        Alert alert = test.driver.switchTo().alert();
        alert.accept();
        return this;
    }

    public MyProfilePage validatePassedExam(String categoryName) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd");
        test.validateTextPresent(simpleDateFormat.format(new Date()));
        test.validateTextPresent("100.0%");
        test.validateTextPresent(categoryName);
        return this;
    }
}
