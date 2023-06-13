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
    }

    public void goToWishList(){
        addToWishListHeaderIcon.click();
    }



}
