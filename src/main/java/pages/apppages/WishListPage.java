package pages.apppages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

import java.util.List;

public class WishListPage extends BasePage {

    @FindBy(xpath = "//div[@class='card-wrapper']")
    private List<WebElement> itemsAddedToWishList;

    @FindBy(xpath = "//a[@class='btn-round-close']")
    private WebElement removeFromWishListButton;

    @FindBy(xpath = "//h1[@class='headline headline--level2']")
    private WebElement sorryYouHaveNotAddedMessage;
    public WishListPage(WebDriver driver) {
        super(driver);
    }

    public int getNumberOfItemsAddedToWishList() {
        return itemsAddedToWishList.size();
    }

    public void removeOneItemFromWishList() {
        removeFromWishListButton.click();
        waitForActionToBeCompleted();
    }

    public String getSorryYouHaveNotAddedMessageText() {
        return sorryYouHaveNotAddedMessage.getText();
    }
}
