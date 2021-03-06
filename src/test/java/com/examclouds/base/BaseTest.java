package com.examclouds.base;

import com.examclouds.model.*;
import com.examclouds.pageObject.base.BasePage;
import io.ddavison.conductor.Browser;
import io.ddavison.conductor.Config;
import io.ddavison.conductor.Locomotive;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tatyana on 22.05.2016.
 */
@Config(
        browser = Browser.CHROME,
        url = "http://localhost:8080/jpa-1.0/"
)
public class BaseTest extends Locomotive {
    protected Article article1 = new Article("article1Key", "article1Desc", "article1Title", "article1Text", "article1Img");
    protected Article article2 = new Article("article2Key", "article2Desc", "article2Title", "article2Text", "article2Img");
    protected Test test1 = new Test("test1", "TEST1Тест1", "#test1#тест1", "itemText1", article1, "ru");
    protected Test test2 = new Test("test2", "TEST2", "#test2", "itemText2", article2, "ru");
    protected Test testOCEJWSD = new Test("web-services", "Web Services");
    protected Category category1 = new Category("Category1", "category1", testOCEJWSD, "categoryDescription");

    protected Test ocpjp8 = new Test("ocpjp8", "OCPJP 8");
    protected Test testJPA = new Test("jpa", "Java Persistence API");
    protected Category category2 = new Category("Category2", "category2", ocpjp8, "categoryDescription");
    protected Category category3 = new Category("Category3", "category3", testJPA, "categoryDescription");
    protected QuestionEntry questionEntry1 = new QuestionEntry("question2", "answer2");
    protected static TestQuestionEntry[] testQuestionEntries = new TestQuestionEntry[3];
    protected static TestQuestionEntry testQuestionEntry;

    static {
        testQuestionEntries[0] = initializeTestQuestionEntry("questionвопрос444",
                new String[]{"answerответ411", "answerответ422", "answerответ433"},
                new boolean[]{true, true, true});
        testQuestionEntries[1] = initializeTestQuestionEntry("questionвопрос555",
                new String[]{"answerответ511", "answerответ522", "answerответ533"},
                new boolean[]{true, true, true});
        testQuestionEntries[2] = initializeTestQuestionEntry("questionвопрос6",
                new String[]{"answerответ611", "answerответ622", "answerответ633"},
                new boolean[]{true, true, true});
        testQuestionEntry = initializeTestQuestionEntry("questionвопрос7",
                new String[]{"answerответ711", "answerответ722", "answerответ733"},
                new boolean[]{true, true, true});
    }

    private static TestQuestionEntry initializeTestQuestionEntry(String question, String[] answersArray, boolean[] isCorrect) {
        List<Answer> answers = new ArrayList<>();
        answers.add(new Answer(answersArray[0], isCorrect[0]));
        answers.add(new Answer(answersArray[1], isCorrect[1]));
        answers.add(new Answer(answersArray[2], isCorrect[2]));
        return new TestQuestionEntry(question, answers);
    }

    public void testMenuAfterSysadminLogin(BasePage test) {
        test.openLoginPage()
                .sysadminLogin()
                .menuExist()
                .profileTabPresent()
                .adminTabPresent()
                .logoutPresent();
    }

    public void testMenuAfterUserLogin(BasePage test) {
        test.openLoginPage()
                .userLogin()
                .menuExist()
                .profileTabPresent()
                .adminTabNotPresent()
                .logoutPresent().logout();
    }

    public void testMenuWithoutLogin(BasePage test) {
        test.menuExist()
                .profileTabNotPresent()
                .adminTabNotPresent()
                .loginHrefPresent()
                //.registerHrefPresent()
                .logoutNotPresent();
    }

    public void testMenuWithWrongUserLogin(BasePage test) {
        test.openLoginPage()
                .wrongUserLogin()
                .menuExist()
                .profileTabNotPresent()
                .adminTabNotPresent()
                .logoutNotPresent()
                .wrongMessagePresent();
    }

    public void testMenuWithWrongUserPassword(BasePage test) {
        test.openLoginPage()
                .wrongUserPassword()
                .menuExist()
                .profileTabNotPresent()
                .adminTabNotPresent()
                .logoutNotPresent()
                .wrongMessagePresent();
    }

    public void addCategory(String testPath, Category category, BasePage test) {
        test.openLoginPage()
                .sysadminLogin()
                .openAdminTab()
                .openShowTestPage(testPath)
                .openCreateCategoryPage()
                .setCategoryData(category)
                .clickSave()
                .loadMessagePage()
                .isMessagePresent("The category is created.");
    }

    public void deleteCategory(String testPath, String categoryPath, BasePage adminPage) {
        adminPage.openAdminTab()
                .openShowTestPage(testPath)
                .clickDeleteCategory(categoryPath)
                .loadMessagePage()
                .isMessagePresent("The category is removed.")
                .openTestPage(TestEnum.WS)
                .validateCategoryItemNotPresent(categoryPath);

    }
}
