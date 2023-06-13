package manager;

import org.openqa.selenium.WebDriver;
import pages.apppages.*;

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

    public SearchResultsPage getSearchPage() {
        return new SearchResultsPage(driver);
    }

    public CompareItemsPage getCompareItemsPage() {
        return new CompareItemsPage(driver);
    }

    public WishListPage getWishListPage() {
        return new WishListPage(driver);
    }

    public ProductPage getProductPage() {
        return new ProductPage(driver);
    }

}
