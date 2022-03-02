package tests;

import com.UserOperations;
import com.model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.pageObjects.LogInPage;
import com.pageObjects.MainPage;
import com.pageObjects.RegisterPage;

import java.util.Map;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.Assert.assertTrue;

public class LoginTest {
    static MainPage mainPage = null;
    UserOperations userOperations = new UserOperations();
    User user = new User();

    LogInPage logInPage = page(LogInPage.class);
    RegisterPage registerPage = page(RegisterPage.class);

    private final String CHECKING_CREATE_ORDER_BUTTON_IS_VISIBLE = "Should be visible 'Оформить заказ' button";

    @Before
    public void before() {
        System.setProperty("webdriver.chrome.driver", "driver/yandexdriver.exe");// для тестирования в chrome закоменьть строчку
        mainPage = open(MainPage.URL, MainPage.class);
        Map <String,String> response = userOperations.register();
        user.setEmail(response.get("email"));
        user.setPassword(response.get("password"));
    }

    @Test
    public void loginByButtonPersonalAccount(){

        mainPage.clickOnPersonalAccount();

        logInPage.fillEmailField(user.getEmail());
        logInPage.fillPasswordField(user.getPassword());
        logInPage.clickOnButtonLogIn();

        assertTrue(CHECKING_CREATE_ORDER_BUTTON_IS_VISIBLE,mainPage.isVisibleCreateOrderButton());
    }

    @Test
    public void loginByButtonLoginAccount(){

        mainPage.clickOnLoginInAccount();

        logInPage.fillEmailField(user.getEmail());
        logInPage.fillPasswordField(user.getPassword());
        logInPage.clickOnButtonLogIn();

        assertTrue(CHECKING_CREATE_ORDER_BUTTON_IS_VISIBLE,mainPage.isVisibleCreateOrderButton());
    }

    @Test
    public void loginByButtonInRegistrationMenu(){

        mainPage.clickOnLoginInAccount();

        logInPage.clickOnButtonRegistration();

        registerPage.clickOnLoginButton();

        logInPage.fillEmailField(user.getEmail());
        logInPage.fillPasswordField(user.getPassword());
        logInPage.clickOnButtonLogIn();

        assertTrue(CHECKING_CREATE_ORDER_BUTTON_IS_VISIBLE,mainPage.isVisibleCreateOrderButton());
    }

    @Test
    public void loginByButtonInRecoveryPassword(){

        mainPage.clickOnLoginInAccount();

        logInPage.clickOnRecoveryPasswordButton();

        registerPage.clickOnLoginButton();

        logInPage.fillEmailField(user.getEmail());
        logInPage.fillPasswordField(user.getPassword());
        logInPage.clickOnButtonLogIn();

        assertTrue(CHECKING_CREATE_ORDER_BUTTON_IS_VISIBLE,mainPage.isVisibleCreateOrderButton());
    }
    @After
    public void after(){

        userOperations.delete();
        closeWebDriver();
    }
}
