package com.examclouds.profile;

import com.examclouds.base.BaseTest;
import com.examclouds.model.Person;
import com.examclouds.pageObject.home.HomePage;
import org.junit.Test;

import static com.examclouds.pageObject.base.BasePage.*;

/**
 * Created by Tatyana on 26.05.2016.
 */
public class ProfileTest extends BaseTest {
    HomePage homePage = new HomePage(this);

    @Test
    public void testChangeProfileData() {
        Person person1 = new Person(USER_LOGIN + 1, USER_PASSWORD + 1, USER_FIRST_NAME + 1, USER_LAST_NAME + 1, 1 + USER_EMAIL);
        Person person2 = new Person(USER_LOGIN, USER_PASSWORD, USER_FIRST_NAME, USER_LAST_NAME, USER_EMAIL);
        homePage.openLoginPage()
                .login(USER_LOGIN, USER_PASSWORD)
                .openMyProfile()
                .setPersonData(person1)
                .submitPersonData()
                .acceptSubmitData()
                .verifyPersonData(person1)
                .setPersonData(person2)
                .submitPersonData()
                .acceptSubmitData()
                .verifyPersonData(person2);
    }

    @Test
    public void testChangePassword() {
        homePage.openLoginPage()
                .login(USER_LOGIN, USER_PASSWORD)
                .openMyProfile()
                .openChangePasswordWindow()
                .verifyLoginText(USER_LOGIN)
                .clickConfirm()
                .verifyAlertText("Please enter all required fields.")
                .setPassword(USER_PASSWORD)
                .setConfPassword(USER_PASSWORD + 1)
                .clickConfirm()
                .verifyAlertText("Password should be the same as Confirm Password.")
                .setPassword(USER_PASSWORD + 1)
                .setConfPassword(USER_PASSWORD + 1)
                .clickConfirm()
                .goToMyProfile()
                .logout()
                .openLoginPage()
                .login(USER_LOGIN, USER_PASSWORD + 1)
                .verifyUserLoggedIn(USER_LOGIN)
                .openMyProfile()
                .openChangePasswordWindow()
                .setPassword(USER_PASSWORD)
                .setConfPassword(USER_PASSWORD)
                .clickConfirm()
        ;
    }

    @Test
    public void testAddQuestion() {
        try {
            homePage.openLoginPage()
                    .login(USER_LOGIN, USER_PASSWORD)
                    .openMyProfile()
                    .clickAddAnswer()
                    .selectTest(category1.getTest().getPathName())
                    .selectCategory(category1.getPathName())
                    .addQuestion(questionEntry1)
                    .openMyProfile()
                    .clickMyQuestions()
                    .validateQuestionEntryPresent(questionEntry1)
                    .validateQuestionNotApproved()
                    .showQuestionPicture(category1)
                    .openMyProfile()
                    .clickMyQuestions()
                    .goToQuestion(category1)
                    .openMyProfile()
                    .clickMyQuestions()
                    .openEditQuestionPage()
                    .editQuestion(questionEntry1)
                    .isMessagePresent("The question is updated.");
        } finally {
            homePage.openMyProfile()
                    .clickMyQuestions()
                    .deleteQuestion()
                    .openMyProfile()
                    .clickMyQuestions()
                    .validateQuestionEntryNotPresent(questionEntry1);
        }

    }
}
