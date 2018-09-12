package com.examclouds.pageObject.administration;

import com.examclouds.model.Test;
import com.examclouds.pageObject.administration.article.EditArticlePage;
import com.examclouds.pageObject.administration.question.AddQuestionPage;
import com.examclouds.pageObject.base.BasePage;
import com.examclouds.pageObject.base.MessagePage;
import com.examclouds.pageObject.test.AddTestPage;
import com.examclouds.pageObject.test.EditTestPage;
import io.ddavison.conductor.Locomotive;

/**
 * Created by Tatyana on 25.05.2016.
 */
public class WelcomeAdminPage extends BasePage {
    public static final String LOC_USER_DIV_HREF = "div[id$='user%s'] div a";
    public static final String LOC_USER_DIV = "div[id$='user%s'] div";
    public static final String LOC_TEST_HREF = "a[name='%sAdmin']";
    public static final String LOC_DELETE_ARTICLE_HREF = "a[name='deleteArticle%s']";
    public static final String LOC_UP_TEST_HREF = "a[name='up%s']";
    public static final String LOC_EDIT_TEST_HREF = "a[name='editTest%s']";
    public static final String LOC_DELETE_TEST_BTN = "input[name='delete%s']";
    public static final String LOC_EDIT_ARTICLE_HREF = "a[name='editArticle%s']";
    public static final String LOC_ADD_ARTICLE_HREF = "a[name='addArticle']";
    public static final String LOC_ADD_QUESTION_HREF = "a[name='addQuestion']";
    public static final String LOC_ADD_TEST_HREF = "a[name='addTest']";

    public WelcomeAdminPage(Locomotive baseTest) {
        super(baseTest);
    }

    @Override
    public String getTitle() {
        return "Administration Panel of ExamClouds";
    }

    public WelcomeAdminPage verifyUserExist(String login) {
        test.validateText(String.format(LOC_USER_DIV, login), login + " ");
        return this;
    }

    public WelcomeAdminPage clickDeleteUser(String login) {
        test.click(String.format(LOC_USER_DIV_HREF, login));
        return this;
    }

    public MessagePage deleteUser(String login) {
        return verifyUserExist(login)
                .clickDeleteUser(login)
                .loadMessagePage()
                .isMessagePresent("User is removed.");
    }

    public ShowTestPage openShowTestPage(String testPath) {
        test.click(String.format(LOC_TEST_HREF, testPath));
        return new ShowTestPage(test);
    }

    public WelcomeAdminPage clickDeleteArticle(String articleUrl) {
        test.click(String.format(LOC_DELETE_ARTICLE_HREF, articleUrl));
        return this;
    }

    public EditArticlePage openEditArticle(String articleUrl) {
        test.click(String.format(LOC_EDIT_ARTICLE_HREF, articleUrl));
        return new EditArticlePage(test);
    }

    public EditArticlePage openAddArticle() {
        test.click(LOC_ADD_ARTICLE_HREF);
        return new EditArticlePage(test);
    }

    public AddQuestionPage openAddQuestionPage() {
        test.click(LOC_ADD_QUESTION_HREF);
        return new AddQuestionPage(test);
    }

    public AddTestPage openAddTestPage() {
        test.click(LOC_ADD_TEST_HREF);
        return new AddTestPage(test);
    }

    public WelcomeAdminPage deleteArticle(String articlePath) {
        openAdminTab()
                .clickDeleteArticle(articlePath)
                .loadMessagePage()
                .isMessagePresent("The article is removed.");
        return this;
    }

    public WelcomeAdminPage clickDeleteTest(String testPath) {
        test.click(String.format(LOC_DELETE_TEST_BTN, testPath));
        verifyAlertText("Do you want to delete " + testPath + " test?");
        return this;
    }

    public WelcomeAdminPage clickUpTest(String testPath) {
        test.click(String.format(LOC_UP_TEST_HREF, testPath));
        return this;
    }

    public EditTestPage clickEditTest(String testPath) {
        test.click(String.format(LOC_EDIT_TEST_HREF, testPath));
        return new EditTestPage(test);
    }

    public WelcomeAdminPage verifyTestExist(Test currentTest) {
        test.validateTextPresent(currentTest.getName());
        return this;
    }

    public WelcomeAdminPage verifyTestNotExist(String testName) {
        test.validateTextNotPresent(testName);
        return this;
    }
}
