package pages.apppages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

public class ProductPage extends BasePage {

    @FindBy(xpath = "//h1")
    private WebElement productName;

    @FindBy(xpath = "(//div[@class='p-slap__content'])[2]")
    private WebElement productDescription;

    @FindBy(xpath = "(//div[@data-scroll='CHAR'])[1]")
    private WebElement productCharacteristicsMenuSection;

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public String getProductName() {
        return productName.getText();
    }

    public String getProductDescription() {
        waitVisibilityOfElement(productDescription);
        return productDescription.getText();
    }

    public void chooseProductCharacteristicsMenuSection() {
        productCharacteristicsMenuSection.click();
    }


}
