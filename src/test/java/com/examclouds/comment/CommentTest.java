package com.examclouds.comment;

import com.examclouds.base.BaseTest;
import com.examclouds.model.TestEnum;
import com.examclouds.pageObject.article.ArticlePage;
import com.examclouds.pageObject.base.BasePage;
import com.examclouds.pageObject.category.CategoryPage;
import com.examclouds.pageObject.home.HomePage;
import com.examclouds.pageObject.test.TestPage;
import org.junit.Test;

/**
 * Created by Tatyana on 23.07.2016.
 */
public class CommentTest extends BaseTest {
    public static final String MAIN_PAGE_COMMENT = "Main Page Comment";
    public static final String TEST_PAGE_COMMENT = "Test Page Comment";
    public static final String CATEGORY_PAGE_COMMENT = "Category Page Comment";
    public static final String ARTICLE_PAGE_COMMENT = "Article Page Comment";
    private String categoryName = "Basic Concepts";
    private String categoryPathName = "basic-concepts";
    private String testName = "OCEJWSD 6";
    private HomePage homePage = new HomePage(this);

    @Test
    public void testCreateMainPageComment() {
        homePage.openLoginPage()
                .sysadminLogin()
                .openHomePage();
        processComment(MAIN_PAGE_COMMENT, homePage);
    }

    @Test
    public void testCreateTestComment() {
        TestPage testPage = homePage.openLoginPage()
                .sysadminLogin()
                .openTestPage(TestEnum.WS);
        processComment(TEST_PAGE_COMMENT, testPage);
    }

    @Test
    public void testCreateCategoryComment() {
        CategoryPage categoryPage = homePage.openLoginPage()
                .sysadminLogin()
                .openTestPage(TestEnum.WS)
                .openCategoryPage( testName, categoryPathName);
        processComment(CATEGORY_PAGE_COMMENT, categoryPage);
    }

    @Test
    public void testCreateArticleComment() {
        ArticlePage articlePage = homePage.openLoginPage()
                .sysadminLogin()
                .openArticleListTab()
                .openArticle("publications/basic-authentication-in-soap-web-service.jsp", "Secure(.)+");
        processComment(ARTICLE_PAGE_COMMENT, articlePage);
    }

    private void processComment(String comment, BasePage page) {
        page.postComment(comment)
                .validateCommentPresent(comment)
                .openAdminTab()
                .validateCommentPresent(comment)
                .deleteComment(comment);
    }
}
