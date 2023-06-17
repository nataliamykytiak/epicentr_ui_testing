package pages.apppages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

import java.util.List;

public class SearchResultsPage extends BasePage {

    @FindBy(xpath = "//div[@class='columns product-Wrap card-wrapper ']")
    private List<WebElement> searchResultsList;

    @FindBy(xpath = "//*[@data-tooltip-text='до порiвняння']")
    private List<WebElement> searchResultsAddToCompareListIconsList;

    @FindBy(xpath = "//*[@data-tooltip-text='у бажання']")
    private List<WebElement> searchResultsAddToWishListIconsList;

    @FindBy(xpath = "//a[@class='header__compare-link']")
    private WebElement addToCompareListHeaderIcon;

    @FindBy(xpath = "//span[@class='header__whishes-link-icon']")
    private WebElement addToWishListHeaderIcon;

    @FindBy(xpath = "//button[contains(@class,'js-btn--buy')][contains(text(),'Купити')]")
    private List<WebElement> buyButtonsList;

    @FindBy(xpath = "//span[@class='header__cart-counter']")
    private WebElement cartIcon;

    public SearchResultsPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getSearchResultsListElement(int numberOfElement){
        return searchResultsList.get(numberOfElement);
    }

    public void addProductToCompareList(int number){
        searchResultsAddToCompareListIconsList.get(number).click();
    }

    public void goToCompareList(){
        addToCompareListHeaderIcon.click();
    }

    public void addProductToWishList(int number){
        searchResultsAddToWishListIconsList.get(number).click();
        waitForActionToBeCompleted();
    }

    public void goToWishList(){
        addToWishListHeaderIcon.click();
    }

    public void pressBuyButtonToAddProductToCart(int number){
        buyButtonsList.get(number).click();
        waitForActionToBeCompleted();
    }

    public String getCartCounterValue(){
        return cartIcon.getText();
    }



}
