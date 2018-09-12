package com.examclouds.questionEntry;

import com.examclouds.base.BaseTest;
import com.examclouds.model.Category;
import com.examclouds.model.QuestionEntry;
import com.examclouds.pageObject.home.HomePage;
import org.junit.Test;

/**
 * Created by Tatyana on 08.12.2016.
 */
public class QuestionEntryTest extends BaseTest {
    QuestionEntry questionEntry1 = new QuestionEntry("questionвопрос111", "answerответ111");
    QuestionEntry questionEntry2 = new QuestionEntry("questionвопрос222", "answerответ222");
    QuestionEntry questionEntry3 = new QuestionEntry("questionвопрос333", "answerответ333");
    private HomePage homePage = new HomePage(this);

    @Test
    public void testCreateDeleteQuestion() {

        //add Question
        homePage.openLoginPage()
                .sysadminLogin()
                .openAdminTab()
                .openAddQuestionPage()
                .selectTest(category1.getTest().getPathName())
                .selectCategory(category1.getPathName())
                .addQuestion(questionEntry1)
                //verify question exists and delete it
                .openAdminTab()
                .openShowTestPage(category1.getTest().getPathName())
                .openShowQuestionsPage(category1.getPathName(), category1.getTest().getPathName(), category1.getTitle())
                .validateQuestionEntryPresent(questionEntry1)
                .deleteQuestion()
        ;
    }

    @Test
    public void testCreateEditDeleteQuestion() {
        QuestionEntry questionEntry2 = new QuestionEntry("questionвопрос111Edited", "answerответ111Edited");
        //add Question
        homePage.openLoginPage()
                .sysadminLogin()
                .openAdminTab()
                .openAddQuestionPage()
                .selectTest(category1.getTest().getPathName())
                .selectCategory(category1.getPathName())
                .addQuestion(questionEntry1)
                //edit Question
                .openAdminTab()
                .openShowTestPage(category1.getTest().getPathName())
                .openShowQuestionsPage(category1.getPathName(), category1.getTest().getPathName(), category1.getTitle())
                .openEditQuestionPage()
                .upQuestionEntry()
                .selectTest(category2.getTest().getPathName())
                .selectCategory(category2.getPathName())

                .editQuestion(questionEntry2)
                //
                .openAdminTab()
                .openShowTestPage(category1.getTest().getPathName())
                .openShowQuestionsPage(category1.getPathName(), category1.getTest().getPathName(), category1.getTitle())
                .validateQuestionEntryNotPresent(questionEntry1)
                //verify question exists and delete it
                .openAdminTab()
                .openShowTestPage(category2.getTest().getPathName())
                .openShowQuestionsPage(category2.getPathName(), category2.getTest().getPathName(), category2.getTitle())
                .validateQuestionEntryPresent(questionEntry2)
                .deleteQuestion()
                //verify question doesn't exists
                .openAdminTab()
                .openShowTestPage(category2.getTest().getPathName())
                .openShowQuestionsPage(category2.getPathName(), category2.getTest().getPathName(), category2.getTitle())
                .validateQuestionEntryNotPresent(questionEntry2)
        ;
    }

    @Test
    public void testShowQuestionPage() {
        homePage.openLoginPage()
                .sysadminLogin()
                .openAdminTab()
                .openShowTestPage(category3.getTest().getPathName())
                .openShowQuestionsPage(category3.getPathName(), category3.getTest().getPathName(), category3.getTitle())
                .goToQuestion(category3)
                .validateQuestionEntryPresent(questionEntry1)
                .seeOtherQuestions()
                .validatePage(1, 1);
    }

    @Test
    public void testShowQuestionPicturePage() {
        homePage.openLoginPage()
                .sysadminLogin()
                .openAdminTab()
                .openShowTestPage(category3.getTest().getPathName())
                .openShowQuestionsPage(category3.getPathName(), category3.getTest().getPathName(), category3.getTitle())
                .showQuestionPicture(category3)
                .validateQuestionPresent(questionEntry1)
                .clickReadAnswer()
                .validateQuestionEntryPresent(questionEntry1);
    }

    @Test
    public void testMoveQuestions() {
        homePage.openLoginPage()
                .sysadminLogin()
                .openAdminTab();
        addQuestion(category1, questionEntry1);
        addQuestion(category1, questionEntry2);
        addQuestion(category1, questionEntry3);
        //move questions
        homePage.openAdminTab()
                .openShowTestPage(category1.getTest().getPathName())
                .openMoveQuestionsPage(category1.getPathName())
                .validateTestNamePresent(category1.getTest().getName())
                .validateCategoryNamePresent(category1.getName())
                .selectTest(category1.getTest().getPathName())
                .selectCategory(category1.getPathName())
                .setFromNumber("2")
                .setToNumber("2")
                .saveData()
                .validateMessagePresent("Please select different category.")
                .selectTest(category2.getTest().getPathName())
                .selectCategory(category2.getPathName())
                .setFromNumber("3")
                .setToNumber("2")
                .saveData()
                .validateMessagePresent("'From' cannot be greater than 'To' number. " +
                        "'From' and 'To' should be less than total number of questions in the current category.")
                .selectTest(category2.getTest().getPathName())
                .selectCategory(category2.getPathName())
                .setFromNumber("2")
                .setToNumber("3")
                .successfulSaveData()
                .isMessagePresent("2 questions moved.")
                //Verify questions are moved
                .openAdminTab()
                .openShowTestPage(category1.getTest().getPathName())
                .openShowQuestionsPage(category1.getPathName(), category1.getTest().getPathName(), category1.getTitle())
                .validateQuestionEntryPresent(questionEntry1)
                .validateQuestionEntryNotPresent(questionEntry2)
                .validateQuestionEntryNotPresent(questionEntry3)
                .deleteQuestion()
                //Verify questions are moved
                .openAdminTab()
                .openShowTestPage(category2.getTest().getPathName())
                .openShowQuestionsPage(category2.getPathName(), category2.getTest().getPathName(), category2.getTitle())
                .validateQuestionEntryNotPresent(questionEntry1)
                .validateQuestionEntryPresent(questionEntry2)
                .validateQuestionEntryPresent(questionEntry3)
                //Delete questions
                .deleteQuestion()
                .openAdminTab()
                .openShowTestPage(category2.getTest().getPathName())
                .openShowQuestionsPage(category2.getPathName(), category2.getTest().getPathName(), category2.getTitle())
                .deleteQuestion()

        ;

    }


    private void addQuestion(Category category, QuestionEntry questionEntry) {
        homePage.openAdminTab()
                .openAddQuestionPage()
                .selectTest(category.getTest().getPathName())
                .selectCategory(category.getPathName())
                .addQuestion(questionEntry);
    }
}
