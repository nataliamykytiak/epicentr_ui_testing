package pages.apppages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

public class CartPage extends BasePage {


    @FindBy(xpath = "//p[@class='headline headline--level2-bold basket__headline']")
    private WebElement cartPageName;

    @FindBy(xpath = "//button[contains(text(),'Продовжити покупки')]")
    private WebElement continueShoppingButton;

    @FindBy(xpath = "//button[@class='basket-product__del--link']")
    private WebElement deleteProductFromCartIcon;

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public String getCartPageName(){
        return cartPageName.getText();
    }

    public void pressContinueShoppingButton() {
        continueShoppingButton.click();
        waitForActionToBeCompleted();
    }

    public void pressDeleteProductFromCartIcon() {
        waitForActionToBeCompleted();
        deleteProductFromCartIcon.click();
    }


}
