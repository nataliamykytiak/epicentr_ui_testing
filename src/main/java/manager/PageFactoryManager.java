package manager;

import org.openqa.selenium.WebDriver;
import pages.apppages.HomePage;
import pages.apppages.LogInPage;

public class PageFactoryManager {

    WebDriver driver;

    public PageFactoryManager(WebDriver driver) {
        this.driver = driver;
    }

    public HomePage getHomePage() {
        return new HomePage(driver);
    }

    public LogInPage getLogInPage() { return new LogInPage(driver);}
}
