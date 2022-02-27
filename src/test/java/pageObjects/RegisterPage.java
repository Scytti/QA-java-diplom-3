package pageObjects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class RegisterPage {
    @FindBy(how = How.XPATH, using = "//label[text() = 'Имя']/../input")
    private SelenideElement nameField;

    @FindBy(how = How.XPATH, using = "//label[text() = 'Email']/../input")
    private SelenideElement emailField;

    @FindBy(how = How.XPATH, using = "//label[text() = 'Пароль']/../input")
    private SelenideElement passwordField;

    @FindBy(how = How.XPATH, using = "//button[text()='Зарегистрироваться']")
    private SelenideElement registrationButton;

    @FindBy(how = How.XPATH, using = "//p[text()='Некорректный пароль']")
    private SelenideElement incorrectPasswordText;

    @FindBy(how = How.XPATH, using = "//a[text()='Войти']")
    private SelenideElement loginButton;

    @Step("Заполняем Имя")
    public void fillNameField(String name){ nameField.setValue(name);}

    @Step("Заполняем Email")
    public void fillEmailField(String email){ emailField.setValue(email);}

    @Step("Заполняем пароль")
    public void fillPasswordField(String password){ passwordField.setValue(password);}

    @Step("Кликаем на кнопку регистрации")
    public void clickOnRegistrationButton(){ registrationButton.click();}

    @Step("Кликаем на кнопку входа")
    public void clickOnLoginButton(){ loginButton.click();}

    @Step("Получаем выскакивающий текст, при не правильном пароле")
    public String getValidationMessagePasswordField(){return incorrectPasswordText.getText();}
}
