package tests;

import com.UserOperations;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.pageObjects.LogInPage;
import com.pageObjects.MainPage;
import com.pageObjects.PersonalAccount;

import java.util.Map;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PersonalAccountTest {
    static MainPage mainPage = null;
    UserOperations userOperations = new UserOperations();
    PersonalAccount personalAccount = page(PersonalAccount.class);
    LogInPage logInPage;

    private final String CHECKING_CREATE_ORDER_BUTTON_IS_VISIBLE = "Should be visible 'Оформить заказ' button";
    private final String CHECKING_LOGOUT_BUTTON_IS_VISIBLE = "Should be button 'Выход'";
    private final String LOGIN_BUTTON_TEXT = "Вход";

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
    }

    @Test
    public void checkTransitionByClickingOnPersonalAccountTest(){

        mainPage.clickOnPersonalAccount();

        assertTrue(CHECKING_LOGOUT_BUTTON_IS_VISIBLE,personalAccount.isVisibleLogoutButton());
    }

    @Test
    public void checkClickingOnConstructorFromPersonalAccountTest(){
        mainPage.clickOnPersonalAccount();

        mainPage.clickOnConstructorButton();

        assertTrue(CHECKING_CREATE_ORDER_BUTTON_IS_VISIBLE,mainPage.isVisibleCreateOrderButton());
    }

    @Test
    public void checkClickingOnLogoFromPersonalAccountTest(){
        mainPage.clickOnPersonalAccount();

        mainPage.clickOnStellarLogo();

        assertTrue(CHECKING_CREATE_ORDER_BUTTON_IS_VISIBLE,mainPage.isVisibleCreateOrderButton());
    }

    @Test
    public void checkLogoutFromAccountTest(){
        mainPage.clickOnPersonalAccount();

        personalAccount.clickOnLogoutButton();

        assertEquals("Should be button" + LOGIN_BUTTON_TEXT,logInPage.getTextOnLoginPage(), LOGIN_BUTTON_TEXT);
    }

    @After
    public void after(){

        userOperations.delete();
        closeWebDriver();
    }
}
