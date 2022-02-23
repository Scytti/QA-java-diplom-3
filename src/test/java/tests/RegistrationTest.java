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

import static com.codeborne.selenide.Selenide.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RegistrationTest {
    static MainPage mainPage = null;

    Faker faker = new Faker();
    User user = new User();
    UserOperations userOperations = new UserOperations();

    @Before
    public void before(){
        System.setProperty("webdriver.chrome.driver", "driver/yandexdriver.exe");// для тестирования в chrome закоменьть строчку
        mainPage = open(MainPage.URL, MainPage.class);

        user.setName(faker.name().firstName());
        user.setEmail(faker.internet().emailAddress());
        user.setPassword(faker.internet().password(6,15));;
    }

    @Test
    public void successfulRegistrationTest(){
        mainPage.clickOnPersonalAccount();

        LogInPage logInPage = page(LogInPage.class);
        logInPage.clickOnButtonRegistration();

        RegisterPage registerPage = page(RegisterPage.class);
        registerPage.fillNameField(user.getName());
        registerPage.fillEmailField(user.getEmail());
        registerPage.fillPasswordField(user.getPassword());
        registerPage.clickOnRegistrationButton();

        logInPage.shouldBeVisibleTextIn();
        logInPage.fillEmailField(user.getEmail());
        logInPage.fillPasswordField(user.getPassword());//данная строчка обеспечивает небольшую задержку, иначе метод отчистки поля сработает раньше чем автозаполнение в яндекс браузере
        logInPage.clearingPasswordField();
        logInPage.fillPasswordField(user.getPassword());
        logInPage.clickOnButtonLogIn();

        assertTrue("Should be visible 'Оформить заказ' button",mainPage.isVisibleCreateOrderButton());

        userOperations.login(user.getEmail(), user.getPassword());
        userOperations.delete();
    }

    @Test
    public void registrationWithIncorrectPassword(){
        user.setPassword(faker.internet().password(1,5));
        mainPage.clickOnPersonalAccount();

        LogInPage logInPage = page(LogInPage.class);
        logInPage.clickOnButtonRegistration();

        RegisterPage registerPage = page(RegisterPage.class);
        registerPage.fillNameField(user.getName());
        registerPage.fillEmailField(user.getEmail());
        registerPage.fillPasswordField(user.getPassword());
        registerPage.clickOnRegistrationButton();

        assertEquals("Should be text 'Некорректный пароль'",registerPage.getTextOnSignupPageWhenIncorrectPassword(), "Некорректный пароль");
    }

    @After
    public void after(){
        closeWebDriver();
    }
}
