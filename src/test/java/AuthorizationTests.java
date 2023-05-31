import manager.PageFactoryManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.apppages.HomePage;
import pages.apppages.LogInPage;
import pages.apppages.UserProfilePage;

import static io.github.bonigarcia.wdm.WebDriverManager.chromedriver;


public class AuthorizationTests {

    private static final long DEFAULT_TIMEOUT = 2;
    WebDriver driver;
    PageFactoryManager pageFactoryManager;
    HomePage homePage;
    LogInPage logInPage;
    UserProfilePage userProfilePage;

    @BeforeTest
    @Test
    public void testsSetUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
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

    @DataProvider(name = "logInRegisteredUser")
    public static Object[][] logInRegisteredUserDataProvider() {
        return new Object[][] {
                {
                    "https://epicentrk.ua/", "938702483", "Nataliia.1", "Наталя", "Увійти"
                }
        };
    }

    @DataProvider(name = "logInNotRegisteredUser")
    public static Object[][] logInNotRegisteredUserDataProvider() {
        return new Object[][] {
                {
                        "https://epicentrk.ua/", "93870248", "Nataliia.1", "Невiрний логiн або пароль."
                }
        };
    }


    @Test(dataProvider = "logInRegisteredUser")
    public void loginWithValidCredentialsAsARegisteredUser(final String url, String phoneNumber, String userPassword,
                                                                 String userName, String logInButtonName) {
        homePage = pageFactoryManager.getHomePage();
        homePage.openHomePage(url);
        homePage.waitForPageLoadComplete(DEFAULT_TIMEOUT);
        homePage.clickLogInIcon();
        logInPage = pageFactoryManager.getLogInPage();
        logInPage.enterPhoneNumber(phoneNumber);
        logInPage.enterUserPassword(userPassword);
        logInPage.clickLogInButton();
        pageFactoryManager.getHomePage().waitForPageLoadComplete(DEFAULT_TIMEOUT);
        Assert.assertEquals(homePage.getLogInIconText(), userName);
        homePage.clickLogInIcon();
        userProfilePage = pageFactoryManager.getUserProfilePage();
        userProfilePage.clicklogOutButton();
        Assert.assertEquals(homePage.getLogInIconText(), logInButtonName);
    }

    @Test(dataProvider = "logInNotRegisteredUser")
    public void loginAsNotRegisteredUser(final String url, String phoneNumber, String userPassword, String warning) {
        homePage = pageFactoryManager.getHomePage();
        homePage.openHomePage(url);
        homePage.waitForPageLoadComplete(DEFAULT_TIMEOUT);
        homePage.clickLogInIcon();
        logInPage = pageFactoryManager.getLogInPage();
        logInPage.enterPhoneNumber(phoneNumber);
        logInPage.enterUserPassword(userPassword);
        logInPage.clickLogInButton();
        logInPage.waitForPageLoadComplete(DEFAULT_TIMEOUT);
        Assert.assertEquals(logInPage.getLogInWarning(), warning);
    }









}