package com.examclouds.base;

import com.examclouds.model.Article;
import com.examclouds.model.Category;
import com.examclouds.model.QuestionEntry;
import com.examclouds.model.Test;
import com.examclouds.pageObject.base.BasePage;
import io.ddavison.conductor.Browser;
import io.ddavison.conductor.Config;
import io.ddavison.conductor.Locomotive;

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
    protected Test test1 = new Test("test1", "TEST1Тест1", "#test1#тест1","itemText1", article1);
    protected Test test2 = new Test("test2", "TEST2", "#test2", "itemText2",article2);
    protected Test testOCEJWSD = new Test("web-services", "OCEJWSD 6");
    protected Category category1 = new Category("Category1", "category1", testOCEJWSD);

    protected Test testOCJP = new Test("ocjp", "OCJP 6");
    protected Test testJPA = new Test("jpa", "OCEJPAD 6");
    protected Category category2 = new Category("Category2", "category2", testOCJP);
    protected Category category3 = new Category("Category3", "category3", testJPA);
    protected QuestionEntry questionEntry1 = new QuestionEntry("question2", "answer2");


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
                .logoutPresent();
    }

    public void testMenuWithoutLogin(BasePage test) {
        test.menuExist()
                .profileTabNotPresent()
                .adminTabNotPresent()
                .loginHrefPresent()
                .registerHrefPresent()
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


}
