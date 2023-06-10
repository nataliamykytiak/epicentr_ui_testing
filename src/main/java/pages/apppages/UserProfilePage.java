package pages.apppages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

public class UserProfilePage extends BasePage {

    @FindBy(xpath = "//a[contains(text(),'Вийти')]")
    private WebElement logOutButton;

    public UserProfilePage(WebDriver driver) {
        super(driver);
    }

    public void clickLogOutButton(){
        logOutButton.click();
    }
}
