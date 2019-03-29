package com.examclouds.pageObject.administration;

import com.examclouds.model.*;
import com.examclouds.pageObject.administration.question.EditQuestionPage;
import com.examclouds.pageObject.administration.question.ShowQuestionEntryPage;
import com.examclouds.pageObject.administration.question.ShowQuestionPicturePage;
import com.examclouds.pageObject.base.BasePage;
import com.examclouds.pageObject.base.MessagePage;
import io.ddavison.conductor.Locomotive;

/**
 * Created by Tatyana on 07.06.2016.
 */
public class ShowQuestionsPage extends BasePage {
    public static final String LOC_CLEAR_HISTORY_BTN = "input[name='clearHistory']";
    public static final String LOC_DELETE_QUESTION_HREF = "a[name='deleteQuestion']";
    public static final String LOC_GO_TO_QUESTION_HREF = "a[name='goToQuestion']";
    public static final String LOC_SHOW_PICTURE_QUESTION_HREF = "a[name='showPicture']";
    public static final String LOC_EDIT_QUESTION_HREF = "a[name='editQuestion']";
    public static final String LOC_APPROVE_QUESTION_HREF = "a[name='approveQuestion']";
    private String title;

    public ShowQuestionsPage(Locomotive baseTest, String title) {
        super(baseTest, title);
        this.title = title;
    }

    @Override
    public String getTitle() {
        return title;
    }

    public ShowQuestionsPage clickClearHistory() {
        test.click(LOC_CLEAR_HISTORY_BTN);
        return this;
    }

    public ShowQuestionsPage deleteQuestion() {
        test.click(LOC_DELETE_QUESTION_HREF);
        sleep();
        verifyAlertText("Are you sure you want to delete the question?");
        return this;
    }

    public ShowQuestionEntryPage goToQuestion(Category category) {
        test.click(LOC_GO_TO_QUESTION_HREF);
        return new ShowQuestionEntryPage(test, category.getName() + " - " + category.getTest().getName());
    }

    public ShowQuestionPicturePage showQuestionPicture(Category category) {
        test.click(LOC_SHOW_PICTURE_QUESTION_HREF);
        return new ShowQuestionPicturePage(test, category.getName() + " - " + category.getTest().getName());
    }

    public ShowQuestionsPage validateQuestionEntryNotPresent(AbstractQuestionEntry questionEntry) {
        test.validateTextNotPresent(questionEntry.getQuestion());
        test.validateTextNotPresent(questionEntry.getAnswer());
        return this;
    }

    public ShowQuestionsPage validateQuestionCount(int i) {
        test.validateTextPresent("Total: " + i + " questions.");
        return this;
    }

    public ShowQuestionsPage validateQuestionNotApproved() {
        test.validateTextPresent("Not Approved");
        return this;
    }

    public ShowQuestionsPage validateQuestionEntryPresent(AbstractQuestionEntry questionEntry) {
        test.validateTextPresent(questionEntry.getQuestion());
        test.validateTextPresent(questionEntry.getAnswer());
        return this;
    }

    public ShowQuestionsPage validateQuestionEntryPresent(String question) {
        test.validateTextPresent(question);
        return this;
    }

    public ShowQuestionsPage validateTestQuestionEntryPresent(TestQuestionEntry questionEntry) {
        test.validateTextPresent(questionEntry.getQuestion());
        for (Answer answer : questionEntry.getAnswers()) {
            test.validateTextPresent(answer.getText());
        }
        return this;
    }

    public EditQuestionPage openEditQuestionPage() {
        test.click(LOC_EDIT_QUESTION_HREF);
        return new EditQuestionPage(test);
    }

    public MessagePage approveQuestion() {
        test.click(LOC_APPROVE_QUESTION_HREF);
        verifyAlertText("Are you sure you want to approve the question?");
        return new MessagePage(test);
    }
}
