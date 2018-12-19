package com.examclouds.user;

import com.examclouds.base.BaseTest;
import com.examclouds.model.Person;
import com.examclouds.pageObject.home.HomePage;
import org.junit.Test;

import static com.examclouds.pageObject.base.BasePage.*;
import static com.examclouds.pageObject.base.BasePage.USER_EMAIL;
import static com.examclouds.pageObject.base.BasePage.USER_PASSWORD;

public class UserTest extends BaseTest {
    private HomePage homePage = new HomePage(this);

    @Test
    public void testDeleteUserWithAddedQuestions() {
        Person person = new Person(USER_LOGIN, USER_PASSWORD, USER_FIRST_NAME, USER_LAST_NAME, USER_EMAIL);
        try {
            homePage.openRegisterPage()
                    .register(person, USER_PASSWORD)
                    .openMyProfile().addQuestion()
                    .selectTest("web-services")
                    .selectCategory("basic-concepts")
                    .addQuestion(questionEntry1)
                    .logout();
            homePage.openLoginPage().sysadminLogin().openAdminTab()
                    .clickDeleteUser(USER_LOGIN)
                    .loadMessagePage()
                    .isMessagePresent("User cannot be removed, because he has added questions.")
                    .openAdminTab().viewNotApprovedQuestions().deleteQuestion();
        } finally {
            homePage.openAdminTab()
                    .deleteUser(USER_LOGIN);
        }
    }
}
