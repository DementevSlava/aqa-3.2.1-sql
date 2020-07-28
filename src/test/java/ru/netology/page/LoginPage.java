package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Selenide.*;

public class LoginPage {
    private SelenideElement form = $("form");
    private SelenideElement loginField = form.$("[data-test-id=login] input");
    private SelenideElement passwordField = form.$("[data-test-id=password] input");
    private SelenideElement loginButton = form.$("[data-test-id=action-login]");
    private SelenideElement errorNotification = $("[data-test-id='error-notification']");

    public void openLoginPage() {
        open("http://localhost:9999");
    }

    public VerificationPage validLogin(DataHelper.AuthInfo info) {
        loginField.setValue(info.getLogin());
        passwordField.setValue(info.getPassword());
        loginButton.click();
        return new VerificationPage();
    }

    public void invalidAuth(DataHelper.AuthInfo info) {
        loginField.setValue(info.getLogin());
        passwordField.setValue(info.getPassword());
        loginButton.click();
        errorNotification.shouldBe(Condition.visible);
    }
}
