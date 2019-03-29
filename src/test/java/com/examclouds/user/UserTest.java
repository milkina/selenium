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
                    .isMessagePresent("Person cannot be removed, because he has added questions.")
                    .openAdminTab().viewNotApprovedQuestions().deleteQuestion();
        } finally {
            homePage.openAdminTab()
                    .deleteUser(USER_LOGIN);
        }
    }

    @Test
    public void testApproveQuestion() {
        Person person = new Person(USER_LOGIN, USER_PASSWORD, USER_FIRST_NAME, USER_LAST_NAME, USER_EMAIL);
        addCategory(category1.getTest().getPathName(), category1, homePage);

        try {
            homePage.logout()
                    .openRegisterPage()
                    .register(person, USER_PASSWORD)
                    .openMyProfile().addQuestion()
                    .selectTest(category1.getTest().getPathName())
                    .selectCategory(category1.getPathName())
                    .addQuestion(questionEntry1)
                    .logout();
            homePage.openLoginPage()
                    .sysadminLogin()
                    .openAdminTab()
                    .openShowTestPage(category1.getTest().getPathName())
                    .openShowQuestionsPage(category1.getPathName(), category1.getTest().getPathName()
                            , category1.getTitle())
                    .validateQuestionEntryNotPresent(questionEntry1)
                    .openAdminTab()
                    .viewNotApprovedQuestions()
                    .approveQuestion()
                    .isMessagePresent("The question is approved.")
                    .openAdminTab()
                    .openShowTestPage(category1.getTest().getPathName())
                    .openShowQuestionsPage(category1.getPathName(), category1.getTest().getPathName()
                            , category1.getTitle())
                    .validateQuestionEntryPresent(questionEntry1)
                    .deleteQuestion();
        } finally {
            deleteCategory(category1.getTest().getPathName(), category1.getPathName(), homePage);
            homePage.openAdminTab()
                    .deleteUser(USER_LOGIN);
        }
    }
}
