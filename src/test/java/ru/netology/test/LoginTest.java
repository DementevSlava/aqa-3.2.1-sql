package ru.netology.test;

import lombok.val;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.DashboardPage;
import ru.netology.page.LoginPage;

import java.sql.SQLException;

public class LoginTest {

    @AfterAll
    @DisplayName("Should clean Data Base after login")
    static void cleanBase() throws SQLException {
        val dashboardPage = new DashboardPage();
        dashboardPage.cleanDataBase();
    }

    @Test
    @DisplayName("Should login with code from sms")
    void shouldLoginWithSmsCode() throws SQLException{
        val loginPage = new LoginPage();
        loginPage.openLoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        verificationPage.verificationPage();
        val verificationCode = DataHelper.getVerificationCode();
        val dashboardPage = verificationPage.validVerify(verificationCode);
        dashboardPage.dashboardPage();
    }
}
