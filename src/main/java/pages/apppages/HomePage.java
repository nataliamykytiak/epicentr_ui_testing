package pages.apppages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

public class HomePage extends BasePage {

    public String homePageUrl = "https://epicentrk.ua/";

    @FindBy(xpath = "//header/div[1]/div[6]/div[1]/span[1]/*[1]")
    private WebElement logInIcon;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void openHomePage(String url) {
        driver.get(url);
    }

    public WebElement getLogInIcon(){
        return logInIcon;
    }
    public void clickLogInIcon(){
        logInIcon.click();
    }



}
