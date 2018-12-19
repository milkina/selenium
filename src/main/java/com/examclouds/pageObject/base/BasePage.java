package com.examclouds.pageObject.base;

import com.examclouds.model.Category;
import com.examclouds.model.TestEnum;
import com.examclouds.pageObject.administration.ShowQuestionsPage;
import com.examclouds.pageObject.administration.WelcomeAdminPage;
import com.examclouds.pageObject.article.ArticleListPage;
import com.examclouds.pageObject.article.ArticlePage;
import com.examclouds.pageObject.home.HomePage;
import com.examclouds.pageObject.home.LoginPage;
import com.examclouds.pageObject.profile.MyProfilePage;
import com.examclouds.pageObject.register.RegisterPage;
import com.examclouds.pageObject.test.TestPage;
import com.examclouds.pageObject.test.TestsPage;
import io.ddavison.conductor.Locomotive;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

/**
 * Created by Tatyana on 22.05.2016.
 */
abstract public class BasePage {

    public Locomotive test;

    public static final String LOC_LNK_HOMETAB = "li a[id$='home']";
    public static final String LOC_LNK_MY_PROFILETAB = "li a[href$='/show-user-profile']";
    public static final String LOC_LNK_ADMINTAB = "li a[href$='/administration/welcome.jsp']";
    public static final String LOC_LNK_TESTS = "li a[href$='/tests']";
    public static final String LOC_LNK_ARTICLESTAB = "li a[href$='/articles.jsp']";
    public static final String LOC_LNK_LOGOUT = "a[id$='isLogin']";

    public static final String LOC_WRONG_MESSAGE = "span[id$='wrongMessage']";
    public static final String LOC_PERSON_LOGIN = "span[id$='personLogin']";

    public static final String LOC_COMMENT_TEXTAREA = "textarea[name='commentText']";
    public static final String LOC_COMMENT_BTN = "input[name='addComment']";
    public static final String LOC_COMMENT_DIV = "div[class$='commentBody']";
    public static final String LOC_EDIT_COMMENT_HREF = "div[class$='commentBody'] div a[id$='Edit%s']";
    public static final String LOC_DELETE_COMMENT_HREF = "div[class$='commentBody'] input[type$='checkbox'][value$='%s']";
    public static final String LOC_DELETE_COMMENT_BTN = "input[type$='submit']";
    public static final String LOC_COMMENT_BODY_DIV = "div[class$='commentBody'] div[id$='commentBody%s']";
    public static final String LOC_ARTICLE_HREF = "a[href='%s']";

    //buttons

    public static final By LOC_LNK_REGISTER = By.xpath("//a[@id='registerHref']");
    public static final String LOC_LNK_LOGIN = "a[id='loginHref']";

    public static final String WRONG_USER_LOGIN = "wrongUserLogin";
    public static final String WRONG_USER_PASSWORD = "wrongUserPassword";
    public static final String SYSADMIN_LOGIN = "sysadmin";
    public static final String SYSADMIN_PASSWORD = "NewPass5385";

    public static final String USER_LOGIN = "testUser";
    public static final String USER_PASSWORD = "testPassword";
    public static final String USER_FIRST_NAME = "testFirstName";
    public static final String USER_LAST_NAME = "testLastName";
    public static final String USER_EMAIL = "testEmail";


    public BasePage(Locomotive baseTest) {
        test = baseTest;
    }

    public BasePage(Locomotive baseTest, String title) {
        test = baseTest;
        waitForWindow(title);
    }

    public BasePage waitForWindow(String title) {
        test.waitForWindow(title);
        if (!test.driver.getTitle().equals(title)) {
            throw new IllegalStateException("This is not desired page, current page is: "
                    + test.driver.getCurrentUrl());
        }
        return this;
    }

    public RegisterPage openRegisterPage() {
        test.click(LOC_LNK_REGISTER);
        test.waitForElement(LOC_LNK_REGISTER);
        return new RegisterPage(test);
    }

    public LoginPage openLoginPage() {
        test.click(LOC_LNK_LOGIN);
        LoginPage loginPage = new LoginPage(test);
        test.waitForWindow(loginPage.getTitle());
        return loginPage;
    }

    abstract public String getTitle();

    public BasePage verifyUserLoggedIn(String login) {
        test.validatePresent(LOC_PERSON_LOGIN);
        test.validateText(LOC_PERSON_LOGIN, login);
        return this;
    }

    public BasePage logout() {
        test.click(LOC_LNK_LOGOUT);
        //   .waitForElement(LOC_LNK_LOGOUT);
        return this;
    }

    public WelcomeAdminPage openAdminTab() {
        test.click(LOC_LNK_ADMINTAB);
        return new WelcomeAdminPage(test);
    }

    public TestsPage openTestsPage() {
        test.click(LOC_LNK_TESTS);
        return new TestsPage(test);
    }

    public MessagePage loadMessagePage() {
        return new MessagePage(test);
    }

    public BasePage menuExist() {
        test.validatePresent(BasePage.LOC_LNK_HOMETAB)
                .validatePresent("li a[id='ocjp-ocpjp']")
                .validatePresent(TestEnum.JPA.getLocatorName())
                .validatePresent(TestEnum.WS.getLocatorName())
                .validatePresent(BasePage.LOC_LNK_ARTICLESTAB);
        return this;
    }

    public BasePage profileTabPresent() {
        test.validatePresent(BasePage.LOC_LNK_MY_PROFILETAB);
        return this;
    }

    public BasePage profileTabNotPresent() {
        test.validateNotPresent(BasePage.LOC_LNK_MY_PROFILETAB);
        return this;
    }

    public BasePage adminTabPresent() {
        test.validatePresent(BasePage.LOC_LNK_ADMINTAB);
        return this;
    }

    public BasePage adminTabNotPresent() {
        test.validateNotPresent(BasePage.LOC_LNK_ADMINTAB);
        return this;
    }

    public BasePage logoutPresent() {
        test.validatePresent(BasePage.LOC_LNK_LOGOUT);
        return this;
    }

    public BasePage logoutNotPresent() {
        test.validateNotPresent(BasePage.LOC_LNK_LOGOUT);
        return this;
    }


    public BasePage registerHrefPresent() {
        test.validatePresent(BasePage.LOC_LNK_REGISTER);
        return this;
    }

    public BasePage loginHrefPresent() {
        test.validatePresent(BasePage.LOC_LNK_LOGIN);
        return this;
    }

    public BasePage wrongMessagePresent() {
        test.validatePresent(BasePage.LOC_WRONG_MESSAGE);
        return this;
    }

    public MyProfilePage openMyProfile() {
        test.click(LOC_LNK_MY_PROFILETAB);
        return new MyProfilePage(test);
    }

    public BasePage waitForAlert() {
        new WebDriverWait(test.driver, 60)
                .ignoring(NoAlertPresentException.class)
                .until(ExpectedConditions.alertIsPresent());
        return this;
    }

    public BasePage verifyAlertText(String text) {
        waitForAlert();
        Alert alert = test.driver.switchTo().alert();

        Assert.assertEquals(alert.getText(), text);
        alert.accept();
        return this;
    }

    public TestPage openTestPage(TestEnum testEnum) {
        test.click(testEnum.getLocatorName());
        return new TestPage(test, testEnum.getTitleName());
    }

    public BasePage openHomePage() {
        test.click(LOC_LNK_HOMETAB);
        return new HomePage(test);
    }

    public BasePage postComment(String comment) {
        test.setText(LOC_COMMENT_TEXTAREA, comment);
        test.click(LOC_COMMENT_BTN);
        test.waitForWindow(this.getTitle());
        return this;
    }

    public BasePage validateCommentPresent(String text) {
        test.validateTextPresent(text);
        return this;
    }

    public BasePage deleteComment(String text) {
        String id = getLastCommentId();
        test.validateText(String.format(LOC_COMMENT_BODY_DIV, id), text);
        test.check(String.format(LOC_DELETE_COMMENT_HREF, id));
        test.click(LOC_DELETE_COMMENT_BTN);
        return this;
    }

    public String getLastCommentId() {
        return test.getAttribute(LOC_COMMENT_DIV, "id");
    }

    public ArticlePage openArticle(String url, String title) {
        test.click(String.format(LOC_ARTICLE_HREF, url));
        return new ArticlePage(test, title);
    }

    public ArticleListPage openArticleListTab() {
        test.click(LOC_LNK_ARTICLESTAB);
        return new ArticleListPage(test);
    }

    public void setTextArea(String value, String textAreaName) {
        test.driver.switchTo().frame(textAreaName + "_ifr");
        test.driver.findElement(By.id("tinymce")).clear();
        test.driver.findElement(By.id("tinymce")).click();
        test.driver.findElement(By.id("tinymce")).sendKeys(value);
        test.driver.switchTo().parentFrame();
    }

    public void sleep() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

    public ShowQuestionsPage openShowTestQuestionPage(Category category) {
        return openAdminTab()
                .openShowTestPage(category.getTest().getPathName())
                .openShowTestQuestionsPage(category.getPathName(), category.getTest().getPathName(), "Questions " + category.getTitle());
    }

    public ShowQuestionsPage openShowQuestionPage(Category category) {
        return openAdminTab()
                .openShowTestPage(category.getTest().getPathName())
                .openShowQuestionsPage(category.getPathName(), category.getTest().getPathName(), category.getTitle());
    }
}
