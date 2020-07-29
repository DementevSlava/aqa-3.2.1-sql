package ru.netology.test;

import lombok.val;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.LoginPage;

import java.sql.SQLException;

public class LoginTest {

    @AfterAll
    @DisplayName("Should clean Data Base after login")
    static void cleanBase() throws SQLException {
        DataHelper.cleanDataBase();
    }

    @Test
    @DisplayName("Should login with code from sms")
    void shouldLoginWithSmsCode() throws SQLException {
        val loginPage = new LoginPage();
        loginPage.openLoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCode();
        val dashboardPage = verificationPage.validVerify(verificationCode);
        dashboardPage.dashboardPage();
    }

    @Test
    @DisplayName("Should invalid login")
    void shouldInvalidLogin() {
        val loginPage = new LoginPage();
        loginPage.openLoginPage();
        val authInfo = DataHelper.getInvalidLoginAuthInfo();
        loginPage.invalidAuth(authInfo);
    }

    @Test
    @DisplayName("Should invalid pass")
    void shouldInvalidPass() {
        val loginPage = new LoginPage();
        loginPage.openLoginPage();
        val authInfo = DataHelper.getInvalidPasswordAuthInfo();
        loginPage.invalidAuth(authInfo);
    }
}
