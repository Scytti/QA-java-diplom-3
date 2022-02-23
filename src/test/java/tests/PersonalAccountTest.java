package tests;

import com.UserOperations;
import com.model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pageObjects.LogInPage;
import pageObjects.MainPage;
import pageObjects.PersonalAccount;

import java.util.Map;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PersonalAccountTest {
    static MainPage mainPage = null;
    UserOperations userOperations = new UserOperations();
    LogInPage logInPage;

    @Before
    public void before() {
        System.setProperty("webdriver.chrome.driver", "driver/yandexdriver.exe");// для тестирования в chrome закоменьть строчку
        mainPage = open(MainPage.URL, MainPage.class);
        Map<String, String> response = userOperations.register();

        mainPage.clickOnPersonalAccount();
        this.logInPage = page(LogInPage.class);
        logInPage.fillEmailField(response.get("email"));
        logInPage.fillPasswordField(response.get("password"));
        logInPage.clickOnButtonLogIn();
        mainPage.clickOnPersonalAccount();
    }

    @Test
    public void CheckTransitionByClickingOnPersonalAccountTest(){

        PersonalAccount personalAccount = page(PersonalAccount.class);

        assertTrue("Should be button 'Выход'",personalAccount.isVisibleLogoutButton());
    }

    @Test
    public void CheckClickingOnConstructorFromPersonalAccountTest(){

        mainPage.clickOnConstructorButton();

        assertTrue("Should be visible 'Оформить заказ' button",mainPage.isVisibleCreateOrderButton());
    }

    @Test
    public void CheckClickingOnLogoFromPersonalAccountTest(){

        mainPage.clickOnStellarLogo();

        assertTrue("Should be visible 'Оформить заказ' button",mainPage.isVisibleCreateOrderButton());
    }

    @Test
    public void CheckLogoutFromAccountTest(){

        PersonalAccount personalAccount = page(PersonalAccount.class);
        personalAccount.clickOnLogoutButton();

        assertEquals("Should be text 'Вход'",logInPage.getTextOnLoginPage(), "Вход");
    }

    @After
    public void after(){

        userOperations.delete();
        closeWebDriver();
    }
}
