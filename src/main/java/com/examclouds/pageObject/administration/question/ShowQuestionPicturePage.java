package com.examclouds.pageObject.administration.question;

import com.examclouds.model.QuestionEntry;
import com.examclouds.pageObject.base.BasePage;
import io.ddavison.conductor.Locomotive;

/**
 * Created by Tatyana on 15.12.2016.
 */
public class ShowQuestionPicturePage extends BasePage {
    private static final String LOC_READ_ANSWER_HREF = "a[id='readAnswer']";
    private String title;

    public ShowQuestionPicturePage(Locomotive baseTest, String title) {
        super(baseTest, title);
        this.title = title;
    }

    @Override
    public String getTitle() {
        return title;
    }

    public ShowQuestionPicturePage validateQuestionPresent(QuestionEntry questionEntry) {
        test.validateTextPresent(questionEntry.getQuestion());
        return this;
    }

    public ShowQuestionEntryPage clickReadAnswer() {
        test.click(LOC_READ_ANSWER_HREF);
        return new ShowQuestionEntryPage(test, title);
    }
}
