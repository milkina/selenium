package com.examclouds.pageObject.administration.question;

import com.examclouds.model.QuestionEntry;
import com.examclouds.pageObject.base.BasePage;
import com.examclouds.pageObject.base.MessagePage;
import io.ddavison.conductor.Locomotive;
import junit.framework.Assert;
import org.openqa.selenium.By;

/**
 * Created by Tatyana on 09.12.2016.
 */
public class EditQuestionPage extends AddQuestionPage {
    public static final String LOC_UP_HREF = "a[id='up']";
    public static final String LOC_DELETE_BTN = "input[id='deleteAnswer%s']";

    @Override
    public String getTitle() {
        return "Edit Question";
    }

    public EditQuestionPage(Locomotive baseTest) {
        super(baseTest);
    }

    public MessagePage editQuestion(QuestionEntry questionEntry) {
        setQuestionText(questionEntry.getQuestion());
        setAnswerText(questionEntry.getAnswer());
        return clickOk("The question is updated.");
    }

    public EditQuestionPage deleteAnswer(int id) {
        test.click(String.format(LOC_DELETE_BTN, id));
        return this;
    }

    public EditQuestionPage selectTest(String testPath) {
        super.selectTest(testPath);
        return this;
    }

    public EditQuestionPage selectCategory(String categoryPath) {
        super.selectCategory(categoryPath);
        return this;
    }

    public EditQuestionPage upQuestionEntry() {
        test.click(LOC_UP_HREF);
        return this;
    }
}
