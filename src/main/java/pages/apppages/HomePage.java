package pages.apppages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

public class HomePage extends BasePage {

    public String homePageUrl = "https://epicentrk.ua/";

    @FindBy(xpath = "//span[@class='header__login-opener-icon']")
    private WebElement logInIcon;

    @FindBy(xpath = "//span[@class='header__login-opener-text']")
    private WebElement logInIconUsername;







    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void openHomePage(String url) {
        driver.get(url);
    }

    public void clickLogInIcon(){
        logInIcon.click();
    }

    public String getLogInIconText () {
        return logInIconUsername.getText();
    }




}
