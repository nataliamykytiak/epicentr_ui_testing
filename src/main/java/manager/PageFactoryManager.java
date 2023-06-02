package manager;

import org.openqa.selenium.WebDriver;
import pages.apppages.HomePage;
import pages.apppages.LogInPage;
import pages.apppages.UserProfilePage;

public class PageFactoryManager {

    private WebDriver driver;

    public PageFactoryManager(WebDriver driver) {
        this.driver = driver;
    }

    public HomePage getHomePage() {
        return new HomePage(driver);
    }
    public LogInPage getLogInPage() {
        return new LogInPage(driver);
    }

    public UserProfilePage getUserProfilePage() {
        return new UserProfilePage(driver);
    }

}
