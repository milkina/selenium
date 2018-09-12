package com.examclouds.article;

import com.examclouds.base.BaseTest;
import com.examclouds.pageObject.administration.WelcomeAdminPage;
import org.junit.Test;

/**
 * Created by Tatyana on 29.06.2016.
 */
public class ArticleTest extends BaseTest {

    public static final String IMAGE_URL = "/imageUrl";
    public static final String URL = "url";
    public static final String KEYWORDS = "key,key2,ключ";
    public static final String DESCRIPTION = "description,описание";
    public static final String ARTICLE_UPDATED_MSG = "The article is added/updated.";
    public static final String TITLE = "title,заголовок";
    WelcomeAdminPage adminPage = new WelcomeAdminPage(this);

    @Test
    public void addEditDeleteArticle() {
        adminPage.openLoginPage()
                .sysadminLogin();
        addArticle();
        editArticle();
        adminPage.deleteArticle("/" + URL + 1);
    }

    private void addArticle() {
        adminPage.openAdminTab()
                .openAddArticle()
                .setImage(IMAGE_URL)
                .setDescription(DESCRIPTION)
                .setKeywords(KEYWORDS)
                .setUrl(URL)
                .setTitle(TITLE)
                .clickSaveBtn()
                .loadMessagePage()
                .isMessagePresent(ARTICLE_UPDATED_MSG);
    }

    private void editArticle() {
        adminPage.openAdminTab()
                .openEditArticle("/" + URL)
                .validateImage(IMAGE_URL)
                .validateUrl(URL)
                .validateDescription(DESCRIPTION)
                .validateKeywords(KEYWORDS)
                .validateTitle(TITLE)
                .setImage(IMAGE_URL + 1)
                .setUrl(URL + 1)
                .setDescription(DESCRIPTION + 1)
                .setKeywords(KEYWORDS + 1)
                .setTitle(TITLE+1)
                .clickSaveBtn()
                .loadMessagePage()
                .isMessagePresent(ARTICLE_UPDATED_MSG);
    }
}
