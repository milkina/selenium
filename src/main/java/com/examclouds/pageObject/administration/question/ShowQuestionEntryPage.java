package com.examclouds.pageObject.administration.question;

import com.examclouds.model.QuestionEntry;
import com.examclouds.pageObject.base.BasePage;
import com.examclouds.pageObject.exam.ShowExamQuestionPage;
import io.ddavison.conductor.Locomotive;

/**
 * Created by Tatyana on 15.12.2016.
 */
public class ShowQuestionEntryPage extends BasePage {
    private static final String LOC_SEE_OTHER_QUESTIONS_HREF = "a[name='seeOtherQuestions']";
    private String title;

    public ShowQuestionEntryPage(Locomotive baseTest, String title) {
        super(baseTest, title);
        this.title = title;
    }

    @Override
    public String getTitle() {
        return title;
    }

    public ShowQuestionEntryPage validateQuestionEntryPresent(QuestionEntry questionEntry) {
        test.validateTextPresent(questionEntry.getQuestion());
        test.validateTextPresent(questionEntry.getAnswer());
        return this;
    }

    public ShowExamQuestionPage seeOtherQuestions() {
        test.click(LOC_SEE_OTHER_QUESTIONS_HREF);
        return new ShowExamQuestionPage(test, title);
    }
}
