package com.examclouds.pageObject.register;

import com.examclouds.model.Person;
import com.examclouds.pageObject.base.BasePage;
import io.ddavison.conductor.Locomotive;
import org.openqa.selenium.By;

/**
 * Created by Tatyana on 23.05.2016.
 */
public class RegisterPage extends BasePage {
    public static final String TITLE = "Register Form on ExamClouds";
    public static final String LOC_INPUT_LOGIN = "input[name$='NEW_LOGIN']";
    public static final String LOC_INPUT_PASSWORD = "input[name$='NEW_PASSWORD']";
    public static final String LOC_INPUT_CONF_PASSWORD = "input[name$='confPassword']";
    public static final String LOC_INPUT_FIRST_NAME = "input[name$='firstName']";
    public static final String LOC_INPUT_LAST_NAME = "input[name$='lastName']";
    public static final String LOC_INPUT_EMAIL = "input[name$='email']";

    public static final String LOC_SUBMIT_BTN = "input[name$='Confirm']";

    public static final By LOC_SPAN_WRONG_MSG = By.xpath("//span[@id='registerWrongMessage']");

    public static final String LOGIN_EXIST_MSG = "Such login already exists.";
    public static final String PASSWORD_WRONG_MSG = "Password and Confirm Password should be the same. Please re-enter.";
    public static final String LOGIN = "someLogin";
    public static final String EXIST_LOGIN = "sad";
    public static final String PASSWORD = "somePassword";
    public static final String FIRST_NAME = "firstName";
    public static final String LAST_NAME = "lastName";
    public static final String EMAIL = "email@gmail.com";

    public RegisterPage(Locomotive baseTest) {
        super(baseTest);

    }

    @Override
    public String getTitle() {
        return TITLE;
    }

    public RegisterPage setLogin(String login) {
        test.setText(LOC_INPUT_LOGIN, login);
        return this;
    }

    public RegisterPage setPassword(String password) {
        test.setText(LOC_INPUT_PASSWORD, password);
        return this;
    }

    public RegisterPage setConfPassword(String password) {
        test.setText(LOC_INPUT_CONF_PASSWORD, password);
        return this;
    }

    public RegisterPage setFirstName(String firstName) {
        if (firstName != null) {
            test.setText(LOC_INPUT_FIRST_NAME, firstName);
        }
        return this;
    }

    public RegisterPage setLastName(String lastName) {
        if (lastName != null) {
            test.setText(LOC_INPUT_LAST_NAME, lastName);
        }
        return this;
    }

    public RegisterPage setEmail(String email) {
        test.setText(LOC_INPUT_EMAIL, email);
        return this;
    }

    public RegisterPage setPerson(Person person) {
        setLogin(person.getLogin())
                .setPassword(person.getPassword())
                .setFirstName(person.getFirstName())
                .setLastName(person.getLastName())
                .setEmail(person.getEmail());
        return this;
    }

    public RegisterPage submitData() {
        test.click(LOC_SUBMIT_BTN);
        return this;
    }

    public WelcomeRegisterPage loadWelcomeRegisterPage() {
        WelcomeRegisterPage welcomeRegisterPage = new WelcomeRegisterPage(test);
        test.waitForWindow(welcomeRegisterPage.getTitle());
        return welcomeRegisterPage;
    }

    public RegisterPage verifyWrongMessage(String message) {
        test.waitForElement(LOC_SPAN_WRONG_MSG);
        test.validateText(LOC_SPAN_WRONG_MSG, message);
        return this;
    }

    public RegisterPage register(Person person, String confPassword) {
        return setPerson(person)
                .setConfPassword(confPassword)
                .submitData();
    }
}
