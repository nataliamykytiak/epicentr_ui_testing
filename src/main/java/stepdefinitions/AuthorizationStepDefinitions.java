package stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import manager.PageFactoryManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.apppages.HomePage;
import pages.apppages.LogInPage;

import static io.github.bonigarcia.wdm.WebDriverManager.chromedriver;
import static org.testng.Assert.*;

public class AuthorizationStepDefinitions {

    private static final long DEFAULT_TIMEOUT = 2;
    WebDriver driver;
    PageFactoryManager pageFactoryManager;
    HomePage homePage;
    LogInPage logInPage;

    @Before
    public void testsSetUp() {
        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--headless=new");
        chromedriver().setup();
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        pageFactoryManager = new PageFactoryManager(driver);
    }

    @After
    public void tearDown() {
        driver.close();
    }

    @When("User opens {string} page")
    public void openPage(final String url) {
        homePage = pageFactoryManager.getHomePage();
        homePage.openHomePage(url);
        homePage.waitForPageLoadComplete(DEFAULT_TIMEOUT);
    }

    @When("User clicks Log in icon")
    public void userClicksLogInIcon() {
        homePage.clickLogInIcon();
        logInPage = pageFactoryManager.getLogInPage();
    }
    @When("User enters phone number {string}")
    public void userEntersPhoneNumber(String string) {
        logInPage.enterPhoneNumber(string);
    }
    @When("User enters password {string}")
    public void userEntersPassword(String string) {
        logInPage.enterUserPassword(string);
    }
    @When("User clicks Log in button")
    public void userClicksLogInButton() {
        logInPage.clickLogInButton();
    }
    @Then("User checks that Log in icon text equals {string}")
    public void userChecksThatLogInIconTextEquals(String string) {
        homePage = pageFactoryManager.getHomePage();
        homePage.getLogInIconText();
    }


}
