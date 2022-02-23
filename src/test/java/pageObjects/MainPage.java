package pageObjects;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.cssValue;
import static com.codeborne.selenide.Condition.visible;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;

public class MainPage {
    //URL сайта
    public static final String URL = "https://stellarburgers.nomoreparties.site/";

    String activeTabColor = "rgba(255, 255, 255, 1)";

    @FindBy(how = How.XPATH, using = "//p[text()='Личный Кабинет']")
    private SelenideElement personalAccount;

    @FindBy(how = How.XPATH, using = "//button[text()='Войти в аккаунт']")
    private SelenideElement loginInAccount;

    @FindBy(how = How.XPATH, using = "//button[text()='Оформить заказ']")
    private SelenideElement createOrderButton;

    @FindBy(how = How.XPATH, using = "//p[text()='Конструктор']")
    private SelenideElement constructorButton;

    @FindBy(how = How.XPATH, using = "//h1[text()='Соберите бургер']")
    private SelenideElement createBurgerText;

    @FindBy(how = How.CLASS_NAME, using = "AppHeader_header__logo__2D0X2")
    private SelenideElement stellarLogo;

    @FindBy(how = How.XPATH, using = "//span[text()='Булки']")
    private SelenideElement bunButton;

    @FindBy(how = How.XPATH, using = "//span[text()='Соусы']")
    private SelenideElement sauceButton;

    @FindBy(how = How.XPATH, using = "//span[text()='Начинки']")
    private SelenideElement fillingButton;

    @Step("Кликаем на личный кабиент")
    public void clickOnPersonalAccount(){ personalAccount.click();}

    @Step("Кликаем на 'Вход в аккаунт'")
    public void clickOnLoginInAccount(){ loginInAccount.click();}

    @Step("Кликаем на конструктор")
    public void clickOnConstructorButton(){ constructorButton.click();}

    @Step("Кликаем на логотип")
    public void clickOnStellarLogo(){ stellarLogo.click();}

    @Step("Проверяем видимость кнопки 'Оформить заказ'")
    public boolean isVisibleCreateOrderButton(){return createOrderButton.shouldBe(visible).isDisplayed();}

    @Step("Кликаем на вкладку соусы")
    public void clickOnSauceTab(){ sauceButton.click();}

    @Step("Проверяем что вкладка соусы активизировалась")
    public boolean sauceTabIsActive(){return sauceButton.shouldHave(cssValue("color",activeTabColor)).isDisplayed();}

    @Step("Кликаем на вкладку былки")
    public void clickOnBunTab(){ bunButton.click();}

    @Step("Проверяем что вкладка булки активизировалась")
    public boolean bunTabIsActive(){return bunButton.shouldHave(cssValue("color",activeTabColor)).isDisplayed();}

    @Step("Кликаем на вкладку начинки")
    public void clickOnFillingTab(){ fillingButton.click();}

    @Step("Проверяем что вкладка начинки активизировалась")
    public boolean fillingTabIsActive(){return fillingButton.shouldHave(cssValue("color",activeTabColor)).isDisplayed();}
}
