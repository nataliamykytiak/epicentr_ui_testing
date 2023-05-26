package pages.apppages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

public class LogInPage extends BasePage {

    @FindBy(xpath = "//input[@id='user_login']")
    private WebElement phoneNumberInputField;

    @FindBy(xpath = "//input[@id='user_login']")
    private WebElement userPasswordInputField;

    @FindBy(xpath = "//input[@id='user_login']")
    private WebElement logInButton;

    public LogInPage(WebDriver driver) {
        super(driver);
    }

    public void enterPhoneNumber(String phoneNumber) {
        phoneNumberInputField.sendKeys(phoneNumber);
    }

    public void enterUserPassword(String password) {
        userPasswordInputField.sendKeys(password);
    }

    public void clickLogInButton() {
        logInButton.click();
    }
}
