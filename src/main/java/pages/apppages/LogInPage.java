package pages.apppages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

public class LogInPage extends BasePage {

    @FindBy(xpath = "//input[@id='user_login']")
    private WebElement phoneNumberInputField;

    @FindBy(xpath = "//input[@id='user_pass']")
    private WebElement userPasswordInputField;

    @FindBy(xpath = "//button[contains(text(),'Увійти')]")
    private WebElement logInButton;

    @FindBy(xpath = "//form[@id='auth_form']")
    private WebElement logInFormText;

    @FindBy(xpath = "//p[@class='headline headline--level2-bold popup-auth__title']")
    private WebElement logInWelcomeText;

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
        waitForActionToBeCompleted();
    }

    public String getLogInWarning() {
        waitVisibilityOfElement(logInFormText);
        String warning = logInFormText.getText();
        return warning.substring(0, warning.indexOf('\n'));
    }

    public String getLogInWelcomeText() {
        return logInWelcomeText.getText();
    }


}
