package tests;

import org.junit.Before;
import org.junit.Test;
import com.pageObjects.MainPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.Assert.assertTrue;

public class ConstructorTest {
    static MainPage mainPage = null;

    @Before
    public void before() {
        System.setProperty("webdriver.chrome.driver", "driver/yandexdriver.exe");// для тестирования в chrome закоменьть строчку
        mainPage = open(MainPage.URL, MainPage.class);
    }

    @Test
    public void bunTabIsActive() {

        mainPage.clickOnFillingTab();
        mainPage.clickOnBunTab();

        assertTrue("Bun tab should be active", mainPage.bunTabIsActive());
    }

    @Test
    public void sauceTabIsActive() {

        mainPage.clickOnSauceTab();

        assertTrue("Sauce tab should be active", mainPage.sauceTabIsActive());
    }

    @Test
    public void fillingTabIsActive() {

        mainPage.clickOnFillingTab();

        assertTrue("Filling tab should be active", mainPage.fillingTabIsActive());
    }
}
