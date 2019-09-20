package com.examclouds.pageObject.administration;

import com.examclouds.model.Test;
import com.examclouds.pageObject.administration.article.EditArticlePage;
import com.examclouds.pageObject.administration.question.AddQuestionPage;
import com.examclouds.pageObject.administration.user.UserHistoryPage;
import com.examclouds.pageObject.base.BasePage;
import com.examclouds.pageObject.base.MessagePage;
import com.examclouds.pageObject.test.AddTestPage;
import com.examclouds.pageObject.test.EditTestPage;
import io.ddavison.conductor.Locomotive;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import static org.testng.Assert.assertEquals;

/**
 * Created by Tatyana on 25.05.2016.
 */
public class WelcomeAdminPage extends BasePage {
    public static final String LOC_USER_DIV_HREF = "div[id$='user%s'] div a";
    public static final String LOC_USER_DIV = "div[id$='user%s'] div";
    public static final String LOC_TEST_HREF = "a[id='%sAdmin']";
    public static final String LOC_DELETE_ARTICLE_HREF = "a[id='deleteArticle%s']";
    public static final String LOC_EDIT_TEST_HREF = "a[id='editTest%s']";
    public static final String LOC_DELETE_TEST_BTN = "input[id='delete%s']";
    public static final String LOC_EDIT_ARTICLE_HREF = "a[id='editArticle%s']";
    public static final String LOC_ADD_ARTICLE_HREF = "a[id='addArticle']";
    public static final String LOC_ADD_QUESTION_HREF = "a[id='addQuestion']";
    public static final String LOC_ADD_TEST_HREF = "a[id='addTest']";
    public static final String LOC_VIEW_NOT_APPROVED_QUESTIONS_HREF = "a[id='viewNotApprovedQuestions']";
    public static final String LOC_SEE_USER_HISTORY_HREF = "a[id='seeHistory%s']";

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
                .isMessagePresent("Person is removed.");
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
        verifyAlertText("Do you want to delete " + testPath + "?");
        return this;
    }

    public WelcomeAdminPage dragTest(String testPath, String nextTestPath) {
        WebElement drag = test.driver.findElement(By.id(testPath));
        WebElement drop = test.driver.findElement(By.id(nextTestPath));
        Actions action = new Actions(test.driver);
        action.clickAndHold(drag).moveToElement(drop).release(drop).perform();
        return this;
    }


    public WelcomeAdminPage validateTestOrder(String testPath1, String testPath2) {
        WebElement element = test.driver.findElement(
                By.cssSelector(String.format("#%s",
                        testPath1)));
        assertEquals(element.getAttribute("id"), testPath2);
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

    public ShowQuestionsPage viewNotApprovedQuestions() {
        test.click(LOC_VIEW_NOT_APPROVED_QUESTIONS_HREF);
        return new ShowQuestionsPage(test, "Questions -");
    }

    public UserHistoryPage seeUserHistory(String login) {
        test.click(String.format(LOC_SEE_USER_HISTORY_HREF, login));
        return new UserHistoryPage(test, "Questions -");
    }
}
