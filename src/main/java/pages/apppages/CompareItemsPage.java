package pages.apppages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

import java.util.List;

public class CompareItemsPage extends BasePage {

    @FindBy(xpath = "//div[@class='custom-product-card']")
    private List<WebElement> itemsAddedToCompareList;

    @FindBy(xpath = "//a[@class='btn-round-close compare-remove']")
    private WebElement removeFromCompareListButton;

    @FindBy(xpath = "//h1")
    private WebElement sorryYouHaveNotAddedMessage;

    public CompareItemsPage(WebDriver driver) {
        super(driver);
    }

    public int getNumberOfItemsAddedToCompareList() {
        return itemsAddedToCompareList.size();
    }

    public void removeOneItemFromCompareList() {
        removeFromCompareListButton.click();
        waitForActionToBeCompleted();
    }

    public String getSorryYouHaveNotAddedMessageText() {
        return sorryYouHaveNotAddedMessage.getText();
    }
}
