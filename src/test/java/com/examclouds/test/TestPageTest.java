package com.examclouds.test;

import com.examclouds.base.BaseTest;
import com.examclouds.model.TestEnum;
import com.examclouds.pageObject.base.BasePage;
import com.examclouds.pageObject.category.CategoryPage;
import com.examclouds.pageObject.home.HomePage;
import org.junit.Test;

import static com.examclouds.pageObject.base.BasePage.USER_LOGIN;
import static com.examclouds.pageObject.base.BasePage.USER_PASSWORD;
import static com.examclouds.pageObject.category.CategoryPage.*;

/**
 * Created by Tatyana on 29.05.2016.
 */
public class TestPageTest extends BaseTest {
    HomePage homePage = new HomePage(this);

    @Test
    public void testTestPagesLoaded() {
            homePage.openTestPage(TestEnum.JPA)
                    .validateCategoryMenuPresent()
                    .validateCommentSectionPresent()
            ;
    }

    @Test
    public void testCategoryPageLoaded() {
        homePage.openTestPage(TestEnum.WS)
                .openCategoryPage(CATEGORY_NAME, TEST_NAME, CATEGORY_PATHNAME)
                .validateStartQuizButtonPresent()
              //  .validateOptionsNotPresent()
                .validateCategoryMenuPresent()
                .validateCommentSectionPresent()
        ;
    }

    @Test
    public void testCategoryPageLoadedWithLoggedInUser() {
        homePage.openLoginPage()
                .login(USER_LOGIN, USER_PASSWORD)
                .openTestPage(TestEnum.WS)
                .openCategoryPage(CATEGORY_NAME, TEST_NAME, CATEGORY_PATHNAME)
                .validateStartQuizButtonPresent()
              //  .clickStartQuizButton()
               // .validateOptionsPresent()
                .validateCategoryMenuPresent()
                .validateCommentSectionPresent()
        ;
    }
}
