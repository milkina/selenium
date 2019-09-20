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
    private QuestionEntry questionEntry1 = new QuestionEntry("questionвопрос111", "answerответ111");
    private QuestionEntry questionEntry2 = new QuestionEntry("questionвопрос222", "answerответ222");
    private QuestionEntry questionEntry3 = new QuestionEntry("questionвопрос333", "answerответ333");

    private HomePage homePage = new HomePage(this);

    @Test
    public void testCreateDeleteQuestion() {
        try {
            addCategory(category1.getTest().getPathName(), category1, homePage);
            homePage.openAdminTab()
                    .openAddQuestionPage()
                    .selectTest(category1.getTest().getPathName())
                    .selectCategory(category1.getPathName())
                    .addQuestion(questionEntry1)
                    //verify question exists and delete it
                    .openAdminTab()
                    .openShowTestPage(category1.getTest().getPathName())
                    .openShowQuestionsPage(category1.getPathName(), category1.getTest().getPathName(), category1.getTitle())
                    .validateQuestionEntryPresent(questionEntry1)
                    .validateQuestionCount(1)
                    .deleteQuestion()
                    .openAdminTab()
                    .openShowTestPage(category1.getTest().getPathName())
                    .openShowQuestionsPage(category1.getPathName(), category1.getTest().getPathName(), category1.getTitle())
                    .validateQuestionCount(0);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            deleteCategory(category1.getTest().getPathName(), category1.getPathName(), homePage);
        }
    }

    @Test
    public void testCreateDeleteTestQuestion() {
        try {
            addCategory(category1.getTest().getPathName(), category1, homePage);
            homePage.openAdminTab()
                    .openAddQuestionPage()
                    .selectTest(category1.getTest().getPathName())
                    .selectCategory(category1.getPathName())
                    .addTestQuestion(testQuestionEntries[0])
                    //verify question exists and delete it
                    .openShowTestQuestionPage(category1)
                    .validateTestQuestionEntryPresent(testQuestionEntries[0])
                    .validateQuestionCount(1)
                    .deleteQuestion()
                    .openShowTestQuestionPage(category1)
                    .validateQuestionCount(0);
        } finally {
            deleteCategory(category1.getTest().getPathName(), category1.getPathName(), homePage);
        }
    }

    @Test
    public void testCreateEditDeleteQuestion() {
        try {
            QuestionEntry questionEntry2 = new QuestionEntry("questionвопрос111Edited", "answerответ111Edited");
            addCategory(category1.getTest().getPathName(), category1, homePage);
            homePage.openAdminTab().logout();
            addCategory(category2.getTest().getPathName(), category2, homePage);
            homePage.openAdminTab()
                    .openAddQuestionPage()
                    .selectTest(category1.getTest().getPathName())
                    .selectCategory(category1.getPathName())
                    .addQuestion(questionEntry1)
                    //edit Question
                    .openAdminTab()
                    .openShowTestPage(category1.getTest().getPathName())
                    .openShowQuestionsPage(category1.getPathName(), category1.getTest().getPathName(), category1.getTitle())
                    .validateQuestionCount(1)
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
                    .validateQuestionCount(0)
                    //verify question exists and delete it
                    .openAdminTab()
                    .openShowTestPage(category2.getTest().getPathName())
                    .openShowQuestionsPage(category2.getPathName(), category2.getTest().getPathName(), category2.getTitle())
                    .validateQuestionEntryPresent(questionEntry2)
                    .validateQuestionCount(1)
                    .deleteQuestion()
                    //verify question doesn't exists
                    .openAdminTab()
                    .openShowTestPage(category2.getTest().getPathName())
                    .openShowQuestionsPage(category2.getPathName(), category2.getTest().getPathName(), category2.getTitle())
                    .validateQuestionEntryNotPresent(questionEntry2)
                    .validateQuestionCount(0);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            deleteCategory(category1.getTest().getPathName(), category1.getPathName(), homePage);
            deleteCategory(category2.getTest().getPathName(), category2.getPathName(), homePage);
        }
    }

    @Test
    public void testChangeTypeQuestion() {
        try {
            addCategory(category1.getTest().getPathName(), category1, homePage);
            homePage.openAdminTab()
                    .openAddQuestionPage()
                    .selectTest(category1.getTest().getPathName())
                    .selectCategory(category1.getPathName())
                    .addTestQuestion(testQuestionEntries[0])
                    //edit Question
                    .openShowTestQuestionPage(category1)
                    .validateQuestionCount(1)
                    .openEditQuestionPage()
                    .deleteAnswer(1)
                    .deleteAnswer(2)
                    .clickOk("The question is updated.")
                    //
                    .openShowTestQuestionPage(category1)
                    .validateQuestionCount(0)
                    .validateQuestionEntryNotPresent(questionEntry1)
                    //verify question exists and delete it
                    .openShowQuestionPage(category1)
                    .validateQuestionCount(1)
                    .validateQuestionEntryPresent(testQuestionEntries[0].getQuestion())
                    .openEditQuestionPage()
                    .addAnswer()
                    .setAnswerText(testQuestionEntries[0].getAnswer(), 2)
                    .setCheckbox(2)
                    .clickOk("The question is updated.")

                    .openShowQuestionPage(category1)
                    .validateQuestionCount(0)
                    .validateQuestionEntryNotPresent(testQuestionEntries[0])

                    .openShowTestQuestionPage(category1)
                    .validateQuestionCount(1)
                    .validateQuestionEntryPresent(testQuestionEntries[0])

                    .deleteQuestion()
                    //verify question doesn't exists
                    .openShowTestQuestionPage(category1)
                    .validateQuestionCount(0)
                    .validateQuestionEntryNotPresent(testQuestionEntries[0]);
        } finally {
            deleteCategory(category1.getTest().getPathName(), category1.getPathName(), homePage);
        }
    }

    @Test
    public void testShowQuestionPage() {
        try {
            addCategory(category3.getTest().getPathName(), category3, homePage);
            homePage.openAdminTab()
                    .openAddQuestionPage()
                    .selectTest(category3.getTest().getPathName())
                    .selectCategory(category3.getPathName())
                    .addQuestion(questionEntry1)
                    .openAdminTab()
                    .openShowTestPage(category3.getTest().getPathName())
                    .openShowQuestionsPage(category3.getPathName(), category3.getTest().getPathName(), category3.getTitle())
                    .goToQuestion(category3)
                    .validateQuestionEntryPresent(questionEntry1)
                    .seeOtherQuestions()
                    .validatePage(1, 1);
        } finally {
            homePage.openAdminTab()
                    .openShowTestPage(category3.getTest().getPathName())
                    .openShowQuestionsPage(category3.getPathName(), category3.getTest().getPathName(), category3.getTitle())
                    .deleteQuestion();
            deleteCategory(category3.getTest().getPathName(), category3.getPathName(), homePage);
        }
    }

    @Test
    public void testShowQuestionPicturePage() {
        try {
            addCategory(category3.getTest().getPathName(), category3, homePage);
            homePage.openAdminTab()
                    .openAddQuestionPage()
                    .selectTest(category3.getTest().getPathName())
                    .selectCategory(category3.getPathName())
                    .addQuestion(questionEntry1);
            homePage.openAdminTab()
                    .openShowTestPage(category3.getTest().getPathName())
                    .openShowQuestionsPage(category3.getPathName(), category3.getTest().getPathName(), category3.getTitle())
                    .showQuestionPicture(category3)
                    .validateQuestionPresent(questionEntry1)
                    .clickReadAnswer()
                    .validateQuestionEntryPresent(questionEntry1);
        } finally {
            homePage.openAdminTab()
                    .openShowTestPage(category3.getTest().getPathName())
                    .openShowQuestionsPage(category3.getPathName(), category3.getTest().getPathName(), category3.getTitle())
                    .deleteQuestion();
            deleteCategory(category3.getTest().getPathName(), category3.getPathName(), homePage);
        }
    }

    @Test
    public void testMoveQuestions() {
        addCategory(category1.getTest().getPathName(), category1, homePage);
        homePage.logout();
        addCategory(category2.getTest().getPathName(), category2, homePage);
        homePage.openAdminTab();
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
                .validateQuestionCount(1)
                .deleteQuestion()
                //Verify questions are moved
                .openAdminTab()
                .openShowTestPage(category2.getTest().getPathName())
                .openShowQuestionsPage(category2.getPathName(), category2.getTest().getPathName(), category2.getTitle())
                .validateQuestionEntryNotPresent(questionEntry1)
                .validateQuestionEntryPresent(questionEntry2)
                .validateQuestionEntryPresent(questionEntry3)
                .validateQuestionCount(2)
                //Delete questions
                .deleteQuestion()
                .openAdminTab()
                .openShowTestPage(category2.getTest().getPathName())
                .openShowQuestionsPage(category2.getPathName(), category2.getTest().getPathName(), category2.getTitle())
                .deleteQuestion();
        deleteCategory(category1.getTest().getPathName(), category1.getPathName(), homePage);
        deleteCategory(category2.getTest().getPathName(), category2.getPathName(), homePage);
    }

    private void addQuestion(Category category, QuestionEntry questionEntry) {
        homePage.openAdminTab()
                .openAddQuestionPage()
                .selectTest(category.getTest().getPathName())
                .selectCategory(category.getPathName())
                .addQuestion(questionEntry);
    }
}
