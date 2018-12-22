package com.examclouds.pageObject.administration;

import com.examclouds.pageObject.administration.category.AddCategoryPage;
import com.examclouds.pageObject.administration.category.CreateCategoryPage;
import com.examclouds.pageObject.administration.category.EditCategoryPage;
import com.examclouds.pageObject.administration.question.AddQuestionPage;
import com.examclouds.pageObject.administration.question.MoveQuestionsPage;
import com.examclouds.pageObject.base.BasePage;
import com.examclouds.pageObject.base.MessagePage;
import io.ddavison.conductor.Locomotive;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * Created by Tatyana on 07.06.2016.
 */
public class ShowTestPage extends BasePage {
    public static final String LOC_SHOW_QUESTIONS_HREF = "a[name='CATEGORY_PATH=%s&TEST_PATH=%s&TYPE=QUESTION']";
    public static final String LOC_SHOW_TEST_QUESTIONS_HREF = "a[name='CATEGORY_PATH=%s&TEST_PATH=%s&TYPE=TEST']";
    public static final String LOC_ADD_QUESTION_HREF = "a[name='addQuestion%s']";
    public static final String LOC_CREATE_CATEGORY_HREF = "a[name='createCategory']";
    public static final String LOC_ADD_CATEGORY_HREF = "a[name='addCategory']";
    public static final String LOC_DELETE_CATEGORY_HREF = "input[name='delete%s']";
    public static final String LOC_DELETE_FROM_TEST_HREF = "input[name='removeFromTest%s']";
    public static final String LOC_EDIT_CATEGORY_HREF = "a[name='edit%s']";
    public static final String LOC_MOVE_QUESTIONS_HREF = "a[name='moveQuestions%s']";

    public ShowTestPage(Locomotive baseTest) {
        super(baseTest);
    }

    @Override
    public String getTitle() {
        return "Edit Test";
    }

    public ShowQuestionsPage openShowQuestionsPage(String categoryPath, String testPath, String title) {
        test.click(String.format(LOC_SHOW_QUESTIONS_HREF, categoryPath, testPath));
        return new ShowQuestionsPage(test, "Questions " + title);
    }

    public ShowQuestionsPage openShowTestQuestionsPage(String categoryPath, String testPath, String title) {
        test.click(String.format(LOC_SHOW_TEST_QUESTIONS_HREF, categoryPath, testPath));
        return new ShowQuestionsPage(test, title);
    }


    public AddQuestionPage openAddQuestionsPage(String categoryPath) {
        test.click(String.format(LOC_ADD_QUESTION_HREF, categoryPath));
        return new AddQuestionPage(test);
    }

    public CreateCategoryPage openCreateCategoryPage() {
        test.click(LOC_CREATE_CATEGORY_HREF);
        return new CreateCategoryPage(test);
    }

    public AddCategoryPage openAddCategoryPage() {
        test.click(LOC_ADD_CATEGORY_HREF);
        return new AddCategoryPage(test);
    }

    public ShowTestPage validateCategoryNotPresent(String categoryPath) {
        test.validateNotPresent(String.format(LOC_DELETE_CATEGORY_HREF, categoryPath));
        return this;
    }

    public ShowTestPage validateCategoryPresent(String categoryPath) {
        test.validatePresent(String.format(LOC_DELETE_CATEGORY_HREF, categoryPath));
        return this;
    }

    public ShowTestPage validateCategoriesOrder(String categoryPath1, String categoryPath2) {
        WebElement element = test.driver.findElement(
                By.cssSelector(String.format("#%s + tr",
                        categoryPath1)));
        assertEquals(element.getAttribute("id"), categoryPath2);
        return this;
    }

    public ShowTestPage clickDeleteCategory(String categoryPath) {
        test.click(String.format(LOC_DELETE_CATEGORY_HREF, categoryPath));
        verifyAlertText("Do you want to delete " + categoryPath + " permanently?");
        return this;
    }

    public ShowTestPage dragCategory(String categoryPath, String nextCategoryPath) {
        WebElement drag = test.driver.findElement(By.id(categoryPath));
        WebElement drop = test.driver.findElement(By.id(nextCategoryPath));
        Actions action = new Actions(test.driver);
        action.clickAndHold(drag).moveToElement(drop).release(drop).perform();
        waitForWindow(this.getTitle());
        return this;
    }

    public MessagePage clickRemoveFromTest(String categoryPath, String testPath) {
        test.click(String.format(LOC_DELETE_FROM_TEST_HREF, categoryPath));
        verifyAlertText("Do you want to delete " + categoryPath + " category from " + testPath + " test?");
        return new MessagePage(test);
    }

    public EditCategoryPage openEditCategoryPage(String categoryPath) {
        test.click(String.format(LOC_EDIT_CATEGORY_HREF, categoryPath));
        return new EditCategoryPage(test);
    }

    public MoveQuestionsPage openMoveQuestionsPage(String categoryPath) {
        test.click(String.format(LOC_MOVE_QUESTIONS_HREF, categoryPath));
        return new MoveQuestionsPage(test);
    }

}
