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

    private final String INCORRECT_PASSWORD_MESSAGE = "Should be text 'Некорректный пароль'";
    private final String CHECKING_CREATE_ORDER_BUTTON_IS_VISIBLE = "Should be visible 'Оформить заказ' button";

    LogInPage logInPage = page(LogInPage.class);
    RegisterPage registerPage = page(RegisterPage.class);

    @Before
    public void before(){
        System.setProperty("webdriver.chrome.driver", "driver/yandexdriver.exe");// для тестирования в chrome закоменьть строчку
        mainPage = open(MainPage.URL, MainPage.class);

        user.setName(faker.name().firstName());
        user.setEmail(faker.internet().emailAddress());
        user.setPassword(faker.internet().password(6,15));;

        mainPage.clickOnPersonalAccount();
    }

    @Test
    public void successfulRegistrationTest(){
        logInPage.clickOnButtonRegistration();

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

        assertTrue(CHECKING_CREATE_ORDER_BUTTON_IS_VISIBLE,mainPage.isVisibleCreateOrderButton());

        userOperations.login(user.getEmail(), user.getPassword());// Логинемся через API, чтобы получить Токен нужный для удаления пользователя
        userOperations.delete();// Удаляю пользователя
    }

    @Test
    public void registrationWithIncorrectPassword(){
        user.setPassword(faker.internet().password(1,5));

        logInPage.clickOnButtonRegistration();

        registerPage.fillNameField(user.getName());
        registerPage.fillEmailField(user.getEmail());
        registerPage.fillPasswordField(user.getPassword());
        registerPage.clickOnRegistrationButton();

        assertEquals(INCORRECT_PASSWORD_MESSAGE,registerPage.getValidationMessagePasswordField(), "Некорректный пароль");
    }

    @After
    public void after(){
        closeWebDriver();
    }
}
