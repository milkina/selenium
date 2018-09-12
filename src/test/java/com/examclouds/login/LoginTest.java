package com.examclouds.login;

import com.examclouds.base.BaseTest;
import com.examclouds.pageObject.home.HomePage;
import org.junit.Test;

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
        testMenuAfterUserLogin(homePage);
    }
}
