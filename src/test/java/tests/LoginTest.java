package tests;

import com.UserOperations;
import com.github.javafaker.Faker;
import com.model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pageObjects.LogInPage;
import pageObjects.MainPage;
import pageObjects.RegisterPage;

import java.util.Map;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.Assert.assertTrue;

public class LoginTest {
    static MainPage mainPage = null;
    UserOperations userOperations = new UserOperations();
    User user = new User();

    @Before
    public void before() {
        System.setProperty("webdriver.chrome.driver", "driver/yandexdriver.exe");// для тестирования в chrome закоменьть строчку
        mainPage = open(MainPage.URL, MainPage.class);
        Map <String,String> response = userOperations.register();
        user.setEmail(response.get("email"));
        user.setPassword(response.get("password"));
    }

    @Test
    public void LoginByButtonPersonalAccount(){

        mainPage.clickOnPersonalAccount();

        LogInPage logInPage = page(LogInPage.class);
        logInPage.fillEmailField(user.getEmail());
        logInPage.fillPasswordField(user.getPassword());
        logInPage.clickOnButtonLogIn();

        assertTrue("Should be visible 'Оформить заказ' button",mainPage.isVisibleCreateOrderButton());
    }

    @Test
    public void LoginByButtonLoginAccount(){

        mainPage.clickOnLoginInAccount();

        LogInPage logInPage = page(LogInPage.class);
        logInPage.fillEmailField(user.getEmail());
        logInPage.fillPasswordField(user.getPassword());
        logInPage.clickOnButtonLogIn();

        assertTrue("Should be visible 'Оформить заказ' button",mainPage.isVisibleCreateOrderButton());
    }

    @Test
    public void LoginByButtonInRegistrationMenu(){

        mainPage.clickOnLoginInAccount();

        LogInPage logInPage = page(LogInPage.class);
        logInPage.clickOnButtonRegistration();

        RegisterPage registerPage = page(RegisterPage.class);
        registerPage.clickOnLoginButton();

        logInPage.fillEmailField(user.getEmail());
        logInPage.fillPasswordField(user.getPassword());
        logInPage.clickOnButtonLogIn();

        assertTrue("Should be visible 'Оформить заказ' button",mainPage.isVisibleCreateOrderButton());
    }

    @Test
    public void LoginByButtonInRecoveryPassword(){

        mainPage.clickOnLoginInAccount();

        LogInPage logInPage = page(LogInPage.class);
        logInPage.clickOnRecoveryPasswordButton();

        RegisterPage registerPage = page(RegisterPage.class);
        registerPage.clickOnLoginButton();

        logInPage.fillEmailField(user.getEmail());
        logInPage.fillPasswordField(user.getPassword());
        logInPage.clickOnButtonLogIn();

        assertTrue("Should be visible 'Оформить заказ' button",mainPage.isVisibleCreateOrderButton());
    }
    @After
    public void after(){

        userOperations.delete();
        closeWebDriver();
    }
}
