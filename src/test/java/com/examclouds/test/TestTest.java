package com.examclouds.test;

import com.examclouds.base.BaseTest;
import com.examclouds.model.Article;
import com.examclouds.pageObject.administration.ShowTestPage;
import com.examclouds.pageObject.administration.WelcomeAdminPage;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.testng.Assert.assertEquals;

/**
 * Created by Tatyana on 19.01.2017.
 */
public class TestTest extends BaseTest {
    WelcomeAdminPage adminPage = new WelcomeAdminPage(this);

    @Test
    public void createMoveDeleteTest() {
        adminPage.openLoginPage()
                .sysadminLogin();
        addTest(test1);
        addTest(test2);

        moveTest(test1.getPathName(), test2.getPathName());
        adminPage.verifyTestExist(test1)
                .verifyTestExist(test2);

        deleteTest(test1);
        deleteTest(test2);
    }

    @Test
    public void createEditDeleteTest() {
        Article article = test1.getArticle();
        Article articleEdited = new Article(article.getKeywords() + 1, article.getDescription() + 1,
                article.getTitle() + 1, article.getText() + 1, article.getImgUrl() + 1);
        com.examclouds.model.Test testEdited = new com.examclouds.model.Test(
                test1.getPathName() + 1, test1.getName() + 1, test1.getTags() + 1, test1.getItomText() + 1, articleEdited,"en");
        adminPage.openLoginPage()
                .sysadminLogin();
        addTest(test1);
        editTest(test1, testEdited);
        deleteTest(testEdited);
    }

    private void addTest(com.examclouds.model.Test test) {
        adminPage.openAdminTab()
                .openAddTestPage()
                .setTestData(test)
                .clickSave()
                .loadMessagePage()
                .isMessagePresent("The test is added.")
                .openAdminTab()
                .verifyTestExist(test);
    }

    private void deleteTest(com.examclouds.model.Test test) {
        adminPage.openAdminTab()
                .clickDeleteTest(test.getPathName())
                .loadMessagePage()
                .isMessagePresent("The test is deleted.")
                .openAdminTab()
                .verifyTestNotExist(test.getName())
        ;
    }

    private void moveTest(String testPath, String nextTestPath) {
        adminPage.openAdminTab()
                .dragTest(testPath, nextTestPath)
                .validateTestOrder(nextTestPath, testPath)
                .dragTest(testPath, nextTestPath)
                .validateTestOrder(testPath, nextTestPath);
    }

    private void editTest(com.examclouds.model.Test test, com.examclouds.model.Test testEdited) {

        adminPage.openAdminTab()
                .clickEditTest(test.getPathName())
                .validateData(test)
                .setTestData(testEdited)
                .clickSave()
                .loadMessagePage()
                .isMessagePresent("The test is changed.")

                .openAdminTab()
                .verifyTestExist(testEdited)
                .clickEditTest(testEdited.getPathName())
                .validateData(testEdited);
    }
}
