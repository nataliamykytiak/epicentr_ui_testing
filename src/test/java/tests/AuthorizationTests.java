package tests;

import manager.PageFactoryManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.apppages.HomePage;
import pages.apppages.LogInPage;

import static io.github.bonigarcia.wdm.WebDriverManager.chromedriver;

public class AuthorizationTests {

    private static final long DEFAULT_TIMEOUT = 2;
    WebDriver driver;
    PageFactoryManager pageFactoryManager;
    HomePage homePage;
    LogInPage logInPage;

    @BeforeTest
    @Test
    public void testsSetUp() {
        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--headless=new");
        options.addArguments("--disable-notifications");
        chromedriver().setup();
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        pageFactoryManager = new PageFactoryManager(driver);
    }

    @AfterTest
    @Test
    public void tearDown() {
        driver.close();
    }


    @Test
    public void loginWithValidCredentials(final String url, String phoneNumber, String userPassword) {
        homePage = pageFactoryManager.getHomePage();
        homePage.openHomePage(url);
        homePage.waitForPageLoadComplete(DEFAULT_TIMEOUT);
        homePage.clickLogInIcon();
        logInPage = pageFactoryManager.getLogInPage();
        logInPage.enterPhoneNumber(phoneNumber);
        logInPage.enterUserPassword(userPassword);
        logInPage.clickLogInButton();
        homePage = pageFactoryManager.getHomePage();
        homePage.getLogInIconText();
    }



}
