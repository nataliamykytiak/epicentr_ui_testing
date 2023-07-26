package pages.apppages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

public class ActionsPage extends BasePage {

    @FindBy(css = "._O-DP._O-DP._bZo4")
    WebElement actionBannerImageAddress;

    public ActionsPage(WebDriver driver) {
        super(driver);
    }

    public String getFormattedImageAddress() {
        String styleAttribute = actionBannerImageAddress.getAttribute("style");
        int startIndex = styleAttribute.indexOf("\"") + 1;
        int endIndex = styleAttribute.lastIndexOf("\"");
        String fullName = styleAttribute.substring(startIndex, endIndex);
        return fullName.substring(fullName.lastIndexOf('/') + 1);
    }
}
