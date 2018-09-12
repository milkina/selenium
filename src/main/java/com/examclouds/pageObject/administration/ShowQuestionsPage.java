package com.examclouds.pageObject.administration;

import com.examclouds.model.Category;
import com.examclouds.model.QuestionEntry;
import com.examclouds.pageObject.administration.question.EditQuestionPage;
import com.examclouds.pageObject.administration.question.ShowQuestionEntryPage;
import com.examclouds.pageObject.administration.question.ShowQuestionPicturePage;
import com.examclouds.pageObject.base.BasePage;
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

    public ShowQuestionsPage validateQuestionEntryNotPresent(QuestionEntry questionEntry) {
        test.validateTextNotPresent(questionEntry.getQuestion());
        test.validateTextNotPresent(questionEntry.getAnswer());
        return this;
    }

    public ShowQuestionsPage validateQuestionEntryPresent(QuestionEntry questionEntry) {
        test.validateTextPresent(questionEntry.getQuestion());
        test.validateTextPresent(questionEntry.getAnswer());
        return this;
    }

    public EditQuestionPage openEditQuestionPage() {
        test.click(LOC_EDIT_QUESTION_HREF);
        return new EditQuestionPage(test);
    }
}
