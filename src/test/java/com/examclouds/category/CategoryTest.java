package com.examclouds.category;

import com.examclouds.base.BaseTest;
import com.examclouds.model.Category;
import com.examclouds.model.TestEnum;
import com.examclouds.pageObject.administration.WelcomeAdminPage;
import org.junit.Test;

/**
 * Created by Tatyana on 18.06.2016.
 */
public class CategoryTest extends BaseTest {
    public static final String CATEGORY_NAME = "New Category";
    public static final String CATEGORY_PATHNAME = "new-category";
    public static final String CATEGORY_KEYWORDS = "key1,key2,key3";
    public static final String CATEGORY_DESCRIPTION = "New Description";
    public static final String CATEGORY_IMAGE = "/url/image";
    public static final String CATEGORY_TEXT = "textТекст";

    private WelcomeAdminPage adminPage = new WelcomeAdminPage(this);

    @Test
    public void addEditDeleteCategory() {
        Category category = new Category(CATEGORY_NAME, CATEGORY_PATHNAME, CATEGORY_DESCRIPTION, CATEGORY_KEYWORDS);
        category.setImage(CATEGORY_IMAGE);
        category.setText(CATEGORY_TEXT);

        addCategory(testOCEJWSD.getPathName(), category, adminPage);
        editCategory(testOCEJWSD.getPathName(), category);
        addRemoveCategoryToTest();
        deleteCategory(testOCEJWSD.getPathName(), CATEGORY_PATHNAME + 1, adminPage);
    }

    @Test
    public void moveCategory() {
        adminPage.openLoginPage()
                .sysadminLogin()
                .openAdminTab()
                .openShowTestPage(testOCEJWSD.getPathName())
                .dragCategory("dom-parser", "jaxp-parent")
                .validateCategoriesOrder("jaxp-parent", "dom-parser")
                .dragCategory("dom-parser", "xml")
                .validateCategoriesOrder("xml", "dom-parser");
    }

    private void editCategory(String testPath, Category category) {
        Category categoryNew = new Category();
        categoryNew.setName(CATEGORY_NAME + 1);
        categoryNew.setPathName(CATEGORY_PATHNAME + 1);
        categoryNew.setKeywords(CATEGORY_KEYWORDS + 1);
        categoryNew.setDescription(CATEGORY_DESCRIPTION + 1);
        if (category.getImage() != null) {
            categoryNew.setImage(CATEGORY_IMAGE + 1);
        }
        if (category.getText() != null) {
            categoryNew.setText(CATEGORY_TEXT + 1);
        }

        adminPage.openAdminTab()
                .openShowTestPage(testPath)
                .validateCategoryPresent(CATEGORY_PATHNAME)
                .openEditCategoryPage(CATEGORY_PATHNAME)
                .validateCategory(category)
                .setCategoryData(categoryNew)
                .clickSave()
                .loadMessagePage()
                .isMessagePresent("The category is changed.")
                .openTestPage(TestEnum.WS)
                .openCategoryPage(testOCEJWSD.getName(), CATEGORY_PATHNAME + 1);
    }

    private void addRemoveCategoryToTest() {
        adminPage.openAdminTab()
                .openShowTestPage(ocpjp8.getPathName())
                .openAddCategoryPage()
                .selectTest(testOCEJWSD.getPathName())
                .selectCategory(CATEGORY_PATHNAME + 1)
                .clickAdd()
                .loadMessagePage()
                .isMessagePresent("The category is added.")
                .openAdminTab()
                .openShowTestPage(ocpjp8.getPathName())
                .validateCategoryPresent(CATEGORY_PATHNAME + 1)
                .clickRemoveFromTest(CATEGORY_PATHNAME + 1, ocpjp8.getPathName())
                .isMessagePresent("The category is removed from the selected test.")
                .openAdminTab()
                .openShowTestPage(ocpjp8.getPathName())
                .validateCategoryNotPresent(CATEGORY_PATHNAME + 1)
                .openAdminTab()
                .openShowTestPage(testOCEJWSD.getPathName())
                .validateCategoryPresent(CATEGORY_PATHNAME + 1);
    }
}
