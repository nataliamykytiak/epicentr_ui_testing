package pages.apppages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

import java.util.List;

public class ProductPage extends BasePage {

    @FindBy(xpath = "//h1")
    private WebElement productName;

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public String getProductName() {
        return productName.getText();
    }


}
