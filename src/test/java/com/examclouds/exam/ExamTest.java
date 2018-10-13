package com.examclouds.exam;

import com.examclouds.base.BaseTest;
import com.examclouds.model.ExamOptions;
import com.examclouds.model.TestEnum;
import com.examclouds.pageObject.exam.ShowExamQuestionPage;
import com.examclouds.pageObject.home.HomePage;
import org.junit.Test;

/**
 * Created by Tatyana on 29.05.2016.
 */
public class ExamTest extends BaseTest {
    HomePage homePage = new HomePage(this);
    String title = "Basic Concepts - OCEJWSD 6";
    String categoryName ="Basic Concepts";
    String categoryPathName ="basic-concepts";
    String testName ="OCEJWSD 6";

    @Test
    public void testStartExamWithLogin() {

        String categoryPath = "basic-concepts";
        String testPath = "web-services";
        homePage.openLoginPage()
                .sysadminLogin()
                .openAdminTab()
                .openShowTestPage(testPath)
                .openShowQuestionsPage(categoryPath, testPath, title)
                .clickClearHistory()
        ;

        ShowExamQuestionPage showExamQuestionPage = homePage
                .openTestPage(TestEnum.WS)
                .openCategoryPage(categoryName,testName,categoryPathName)
                .clickStartQuizButton()
                .validateOptionsPresent()
                .selectOption(ExamOptions.ANSWERED.toString())
                .clickStartQuizButton()
                .loadMessagePage()
                .isMessagePresent("There are no questions selected. Either you have already answered all the questions or the selected category is empty.")
                .openTestPage(TestEnum.WS)
                .openCategoryPage(categoryName,testName,categoryPathName)
                .clickStartQuizButton()
                .clickStartQuizButton()
                .waitForWindow(title);
        Integer number = showExamQuestionPage.getQuestionsNumber();
        for (int i = 1; i < number; i++) {
            showExamQuestionPage.validatePage(i, number)
                    .clickCheckbox()
                    .clickNextButton();
        }
        showExamQuestionPage.clickCheckbox();
        for (int i = number; i > 1; i--) {
            showExamQuestionPage.validatePage(i, number)
                    .clickPreviousButton();
        }

        showExamQuestionPage.openCategoryPage(categoryName,testName,categoryPathName)
                .clickStartQuizButton()
                .selectOption(ExamOptions.NOT_ANSWERED.toString())
                .clickStartQuizButton()
                .loadMessagePage()
                .isMessagePresent("There are no questions selected. Either you have already answered all the questions or the selected category is empty.");
    }

    @Test
    public void testStartExamWithoutLogin() {
        ShowExamQuestionPage showExamQuestionPage =  homePage
                .openTestPage(TestEnum.WS)
                .openCategoryPage(categoryName,testName,categoryPathName)
                .clickStartQuizButton()
                .validateOptionsNotPresent()
                .clickStartQuizButton()
                .waitForWindow(title)
                .clickCheckbox()
                .verifyAlertText("Please log in or register to have a possibility to mark questions.")
        ;
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

}
