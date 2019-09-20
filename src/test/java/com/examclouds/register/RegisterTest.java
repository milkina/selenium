package com.examclouds.register;

import com.examclouds.base.BaseTest;
import com.examclouds.model.Person;
import com.examclouds.pageObject.home.HomePage;
import org.junit.Test;

import static com.examclouds.pageObject.register.RegisterPage.*;

/**
 * Created by Tatyana on 19.05.2016.
 */
public class RegisterTest extends BaseTest {
    HomePage homePage = new HomePage(this);

    @Test
    public void wrongPasswordRegister() {
        Person person = new Person(LOGIN, PASSWORD, EMAIL);
        homePage.openRegisterPage()
                .setPerson(person)
                .setConfPassword(PASSWORD + 1)
                .submitData()
                .verifyWrongMessage(PASSWORD_WRONG_MSG);
    }

    @Test
    public void existLoginRegister() {
        try {
            Person person = new Person(USER_LOGIN, USER_PASSWORD, EMAIL);
            homePage.openRegisterPage().register(person, USER_PASSWORD)
                    .logout();
            homePage.openRegisterPage()
                    .setPerson(person)
                    .setConfPassword(USER_PASSWORD)
                    .submitData()
                    .verifyWrongMessage(LOGIN_EXIST_MSG);
        } finally {
            homePage.openLoginPage().sysadminLogin().openAdminTab().deleteUser(USER_LOGIN);
        }
    }

    @Test
    public void register() {
        Person person = new Person(LOGIN, PASSWORD,  EMAIL);
        homePage.openRegisterPage()
                .register(person, PASSWORD)
                .loadWelcomeRegisterPage()
                .verifyWelcomeText(LOGIN)
                .verifyUserLoggedIn(LOGIN)
                .profileTabPresent()
                .openMyProfile()
                .verifyPersonData(person)
                .logout()
                .openLoginPage()
                .login(LOGIN, PASSWORD)
                .verifyUserLoggedIn(LOGIN)
                .logout()
                .openLoginPage()
                .sysadminLogin()
                .openAdminTab()
                .deleteUser(LOGIN);
    }
}
