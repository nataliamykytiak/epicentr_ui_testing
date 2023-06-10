package helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class MouseHoverHelper {

    private WebDriver driver;
    private Actions actions;

    public MouseHoverHelper(WebDriver driver) {
        this.driver = driver;
        this.actions = new Actions(driver);
    }

    public void hoverOverElement(WebElement element) {
        actions.moveToElement(element).perform();
    }
}
