package com.pageObjects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.conditions.Visible;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class LogInPage {

    //
    @FindBy(how = How.LINK_TEXT, using = "Зарегистрироваться")
    private SelenideElement buttonRegistration;

    @FindBy(how = How.XPATH, using = "//label[text() = 'Email']/../input")
    private SelenideElement emailField;

    @FindBy(how = How.XPATH, using = "//label[text() = 'Пароль']/../input")
    private SelenideElement passwordField;

    @FindBy(how = How.XPATH, using = "//button[text()='Войти']")
    private SelenideElement buttonLogin;

    @FindBy(how = How.XPATH, using = "//h2[text()='Вход']")
    private SelenideElement textIn;

    @FindBy(how = How.XPATH, using = "//a[text()='Восстановить пароль']")
    private SelenideElement recoveryPasswordButton;

    @Step("Кликаем на кнопку регистрации")
    public void clickOnButtonRegistration(){ buttonRegistration.click();}

    @Step("Кликаем на кнопку входа")
    public void clickOnButtonLogIn(){ buttonLogin.click();}

    @Step("Кликаем на кнопку восстановления пароля")
    public void clickOnRecoveryPasswordButton(){ recoveryPasswordButton.click();}

    @Step("Заполняем Email")
    public void fillEmailField(String email){
        emailField.clear();
        emailField.setValue(email);}

    @Step("Очищаем поле с паролем")
    public void clearingPasswordField(){
        passwordField.sendKeys(Keys.CONTROL + "a");
        passwordField.sendKeys(Keys.DELETE);};

    @Step("Заполняем пароль")
    public void fillPasswordField(String password){
        passwordField.setValue(password);}

    @Step("Проверяем наличие надписи Вход")
    public void shouldBeVisibleTextIn(){ textIn.shouldBe(Condition.visible);}

    @Step("Получаем надпись Вход")
    public String getTextOnLoginPage(){return textIn.getText();}
}

