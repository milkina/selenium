package com.examclouds.exam;

import com.examclouds.base.BaseTest;
import com.examclouds.model.ExamOptions;
import com.examclouds.model.Person;
import com.examclouds.model.TestEnum;
import com.examclouds.model.TestQuestionEntry;
import com.examclouds.pageObject.exam.ShowExamQuestionPage;
import com.examclouds.pageObject.exam.ShowTestExamQuestionPage;
import com.examclouds.pageObject.home.HomePage;
import org.junit.Test;

import static com.examclouds.pageObject.register.RegisterPage.*;

/**
 * Created by Tatyana on 29.05.2016.
 */
public class ExamTest extends BaseTest {
    HomePage homePage = new HomePage(this);
    String title = "Basic Concepts - Web Services";
    String categoryName = "Basic Concepts";
    String categoryPathName = "basic-concepts";
    String testName = "Web Services";

    @Test
    public void testStartExamWithLogin() {
        String categoryPath = "basic-concepts";
        String testPath = "web-services";
        Person person = new Person(USER_LOGIN, USER_PASSWORD, USER_EMAIL);
        homePage.openRegisterPage()
                .register(person, USER_PASSWORD)
                .loadWelcomeRegisterPage();
        homePage.openMyProfile()
                .addQuestion()
                .selectTest(testPath)
                .selectCategory(categoryPath)
                .addQuestion(questionEntry1)
                .logout();

        homePage.openLoginPage()
                .sysadminLogin()
                .openAdminTab()
                .openShowTestPage(testPath)
                .openShowQuestionsPage(categoryPath, testPath, title)
                .clickClearHistory();

        ShowExamQuestionPage showExamQuestionPage = homePage
                .openTestPage(TestEnum.WS)
                .openCategoryPage(testName, categoryPathName)
                .clickStartQuizButton()
                .waitForWindow(testName + "\u00a0Questions");
        Integer number = showExamQuestionPage.getQuestionsNumber();
        for (int i = 1; i < number; i++) {
            showExamQuestionPage.validatePage(i, number)
                    .clickCheckbox()
                    .clickNextButton();
        }
        showExamQuestionPage.clickCheckbox();
        for (int i = number; i > 1; i--) {
            showExamQuestionPage.validatePage(i, number)
                    .validateCheckBoxChecked()
                    .clickPreviousButton();
        }
        homePage.openAdminTab()
                .viewNotApprovedQuestions()
                .deleteQuestion();
        homePage.openAdminTab().deleteUser(USER_LOGIN);
    }

    @Test
    public void testStartExamWithoutLogin() {
        ShowExamQuestionPage showExamQuestionPage = homePage
                .openTestPage(TestEnum.WS)
                .openCategoryPage(testName, categoryPathName)
                .clickStartQuizButton()
                //  .validateOptionsNotPresent()
                // .clickStartQuizButton()
                .waitForWindow(testName + "\u00a0Questions")
                .clickCheckbox()
                .verifyAlertText("Please log in or register to have a possibility to mark questions.");
        Integer number = showExamQuestionPage.getQuestionsNumber();
        for (int i = 1; i < number; i++) {
            showExamQuestionPage.validatePage(i, number)
                    .clickNextButton();
        }
        for (int i = number; i > 1; i--) {
            showExamQuestionPage.validatePage(i, number)
                    .clickPreviousButton();
        }
    }

    @Test
    public void testStartTestExamWithLogin() {
        String categoryPath = "basic-concepts";
        String testPath = "web-services";

        Person person = new Person(USER_LOGIN, USER_PASSWORD, USER_EMAIL);
        homePage.openRegisterPage()
                .register(person, USER_PASSWORD)
                .loadWelcomeRegisterPage().logout();
        addTestQuestions(categoryPath, testPath);

        ShowTestExamQuestionPage showTestExamQuestionPage = homePage
                .logout()
                .openLoginPage()
                .login(USER_LOGIN, USER_PASSWORD)
                .openTestPage(TestEnum.WS)
                .openCategoryPage(testName, categoryPathName)
                .clickStartTestButton();
        goThroughQuestions(showTestExamQuestionPage);
        showTestExamQuestionPage.clickFinishBtn(USER_LOGIN)
                .validatePage()
                .openMyProfile()
                .validatePassedExam(categoryName)
                .logout();
        homePage.openLoginPage()
                .sysadminLogin()
                .openAdminTab()
                .seeUserHistory(USER_LOGIN)
                .validateExamResultPresent(categoryName);

        deleteQuestions(categoryPath, testPath);
        deleteNotApprovedQuestions();
        homePage.openAdminTab().deleteUser(USER_LOGIN);
    }

    private void deleteQuestions(String categoryPath, String testPath) {
        for (int i = 0; i < testQuestionEntries.length; i++) {
            homePage.openAdminTab()
                    .openShowTestPage(testPath)
                    .openShowTestQuestionsPage(categoryPath, testPath, "Questions Basic Concepts - Web Services")
                    .deleteQuestion();
        }
    }

    private void deleteNotApprovedQuestions() {
        homePage.openAdminTab()
                .viewNotApprovedQuestions()
                .deleteQuestion();
    }

    private void addTestQuestions(String categoryPath, String testPath) {
        homePage.openLoginPage()
                .userLogin()
                .openMyProfile()
                .addQuestion()
                .selectTest(testPath)
                .selectCategory(categoryPath)
                .addTestQuestion(testQuestionEntry)
                .logout();

        homePage.openLoginPage()
                .sysadminLogin();
        for (TestQuestionEntry testQuestionEntry : testQuestionEntries) {
            addTestQuestion(testPath, categoryPath, testQuestionEntry);
        }
    }

    private void goThroughQuestions(ShowTestExamQuestionPage showTestExamQuestionPage) {
        for (int i = 0; i < testQuestionEntries.length; i++) {
            showTestExamQuestionPage.validatePage(i + 1, testQuestionEntries.length);
            for (int j = 0; j < testQuestionEntries[i].getAnswers().size(); j++) {
                if (testQuestionEntries[i].getAnswers().get(j).isChecked()) {
                    showTestExamQuestionPage.clickCheckbox(j);
                }
            }
            showTestExamQuestionPage.clickAnswerBtn();
        }
    }

    private void addTestQuestion(String testPath, String categoryPath, TestQuestionEntry testQuestionEntry) {
        homePage.openAdminTab()
                .openAddQuestionPage()
                .selectTest(testPath)
                .selectCategory(categoryPath)
                .addTestQuestion(testQuestionEntry);
    }
}
