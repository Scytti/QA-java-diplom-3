package pageObjects;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.visible;

public class PersonalAccount {
    @FindBy(how = How.XPATH, using = "//button[text()='Выход']")
    private SelenideElement logoutButton;

    @Step("Кликаем на кнопку выхода")
    public void clickOnLogoutButton(){ logoutButton.click();}

    @Step("Проверяем видимость кнопки выхода")
    public Boolean isVisibleLogoutButton(){return logoutButton.shouldBe(visible).isDisplayed();}
}
