package pages.apppages;

import org.openqa.selenium.WebDriver;
import pages.BasePage;

public class HomePage extends BasePage {

    public String homePageUrl = "https://epicentrk.ua/";

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void openHomePage(String url) {
        driver.get(url);
    }
}
