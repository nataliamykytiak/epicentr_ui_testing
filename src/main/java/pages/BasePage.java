package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class BasePage {

    protected WebDriver driver;

    protected static final long PAGE_LOAD_TIMEOUT = 10;

    protected static final long ACTION_TIMEOUT = 3;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void waitForPageLoadComplete() {
        new WebDriverWait(driver, Duration.ofSeconds(PAGE_LOAD_TIMEOUT))
                .until(webDriver -> ((JavascriptExecutor) webDriver)
                        .executeScript("return document.readyState").equals("complete"));
    }

    public void waitVisibilityOfElement(WebElement element) {
        new WebDriverWait(driver, Duration.ofSeconds(PAGE_LOAD_TIMEOUT))
                .until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForElementToBeClickable(WebElement element) {
        new WebDriverWait(driver, Duration.ofSeconds(PAGE_LOAD_TIMEOUT))
                .until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitForActionToBeCompleted() {
        new WebDriverWait(driver, Duration.ofSeconds(ACTION_TIMEOUT)).ignoring(StaleElementReferenceException.class)
                .until(driver -> {
            try {
                TimeUnit.SECONDS.sleep(ACTION_TIMEOUT);
                return true;
            } catch (InterruptedException e) {
                e.printStackTrace();
                return false;
            }
        });
    }

    public List<WebElement> waitForAllElementsToBeLocated(String xPath) {
        return new WebDriverWait(driver, Duration.ofSeconds(ACTION_TIMEOUT))
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xPath)));
    }



}
