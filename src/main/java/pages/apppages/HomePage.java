package pages.apppages;

import helpers.MouseHoverHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

import java.util.ArrayList;
import java.util.List;


public class HomePage extends BasePage {


    MouseHoverHelper mouseHoverHelper = new MouseHoverHelper(driver);

    @FindBy(xpath = "//span[@class='header__login-opener-icon']")
    private WebElement logInIcon;

    @FindBy(xpath = "//span[@class='header__login-opener-text']")
    private WebElement logInIconUsername;

    @FindBy(xpath = "//div[@class='header__locations-city']")
    private WebElement currentLocationIcon;

    @FindBy(xpath = "(//div[@class='header__locations-opener'])[1]")
    private WebElement currentLocationIconArrow;

    @FindBy(xpath = "//input[@data-test='search-city']")
    private WebElement enterYourCityInput;

    @FindBy(xpath = "//div[@data-test='search-city-items'][1]")
    private WebElement chooseYourCityDropDownListElement;

    @FindBy(xpath = "//div[@class='_Xv5ePK']")
    private WebElement chooseYourStoreButton;

    @FindBy(xpath = "//div[@class='header__burger']")
    private WebElement catalogMenuBurger;

    @FindBy(xpath = "//div[@class='catalog-menu__level-1-link']")
    private List<WebElement> catalogMenuBurgerSectionsList;



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

    public String getCurrentLocationIconText() {
        return currentLocationIcon.getText();
    }

    public void clickCurrentLocationIcon() {
        waitVisibilityOfElement(currentLocationIconArrow);
        currentLocationIconArrow.click();
    }

    public void enterYourCity(String yourCity) {
        enterYourCityInput.click();
        enterYourCityInput.sendKeys(yourCity);
        waitForActionToBeCompleted();
    }


    public void chooseYourCityFromTheList() {
        mouseHoverHelper.hoverOverElement(chooseYourCityDropDownListElement);
        chooseYourCityDropDownListElement.click();
        waitForActionToBeCompleted();
    }

    public void clickChooseYourStoreButton() {
        chooseYourStoreButton.click();
        waitForActionToBeCompleted();
    }

    public void clickCatalogMenuBurger(){
        catalogMenuBurger.click();
    }

    public List<String> getCatalogMenuBurgerSectionsListTitles(){
        List<String> titles = new ArrayList<>();
        for (WebElement section : catalogMenuBurgerSectionsList) {
            String title = section.getText();
            titles.add(title);
        }
        return titles;
    }


}
