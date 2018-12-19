package com.examclouds.login;

import com.examclouds.base.BaseTest;
import com.examclouds.model.Person;
import com.examclouds.pageObject.home.HomePage;
import org.junit.Test;

import static com.examclouds.pageObject.base.BasePage.*;
import static com.examclouds.pageObject.base.BasePage.USER_EMAIL;
import static com.examclouds.pageObject.base.BasePage.USER_PASSWORD;

/**
 * Created by Tatyana on 27.05.2016.
 */
public class LoginTest extends BaseTest {
    HomePage homePage = new HomePage(this);
    @Test
    public void testMenu() {
        super.testMenuWithoutLogin(homePage);
    }

    @Test
    public void testMenuAfterSysadminLogin() {
        testMenuAfterSysadminLogin(homePage);
    }

    @Test
    public void testMenuWithWrongUserLogin() {
        testMenuWithWrongUserLogin(homePage);
    }

    @Test
    public void testMenuWithWrongUserPassword(){
        testMenuWithWrongUserPassword(homePage);
    }

    @Test
    public void testMenuAfterUserLogin(){
        try {
            Person person = new Person(USER_LOGIN, USER_PASSWORD, USER_FIRST_NAME, USER_LAST_NAME, USER_EMAIL);
            homePage.openRegisterPage()
                    .register(person, USER_PASSWORD)
                    .loadWelcomeRegisterPage().logout();
            testMenuAfterUserLogin(homePage);
        } finally {
            homePage.openLoginPage().sysadminLogin().openAdminTab().deleteUser(USER_LOGIN);
        }
    }
}
