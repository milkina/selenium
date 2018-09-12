package com.examclouds.pageObject.administration.question;

import com.examclouds.model.QuestionEntry;
import com.examclouds.pageObject.base.BasePage;
import com.examclouds.pageObject.base.MessagePage;
import io.ddavison.conductor.Locomotive;
import org.openqa.selenium.By;

/**
 * Created by Tatyana on 08.12.2016.
 */
public class AddQuestionPage extends BasePage {
    public static final String LOC_SAVE_BTN = "input[name$='ok']";
    public static final String THE_QUESTION_IS_ADDED = "The question is added.";
    public static final String LOC_TEST_SELECT = "#TEST_PATH";
    public static final String LOC_CATEGORY_SELECT = "#CATEGORY_PATH";

    public AddQuestionPage(Locomotive baseTest) {
        super(baseTest);
    }

    @Override
    public String getTitle() {
        return "Add Question";
    }


    public MessagePage addQuestion(QuestionEntry questionEntry) {
        setQuestionText(questionEntry.getQuestion());
        setAnswerText(questionEntry.getAnswer());
        return saveData().isMessagePresent(THE_QUESTION_IS_ADDED);
    }

    public AddQuestionPage setQuestionText(String str) {
        setTextArea(str, "QUESTION_TEXT_PARAM");
        return this;
    }



    public AddQuestionPage setAnswerText(String str) {
        setTextArea(str, "ANSWER_TEXT_PARAM");
        return this;
    }

    public MessagePage saveData() {
        test.click(LOC_SAVE_BTN);
        return new MessagePage(test);
    }

    public AddQuestionPage selectTest(String testPath) {
        test.selectOptionByValue(LOC_TEST_SELECT, testPath);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        return this;
    }

    public AddQuestionPage selectCategory(String categoryPath) {
        test.selectOptionByValue(LOC_CATEGORY_SELECT, categoryPath);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        return this;
    }
}
