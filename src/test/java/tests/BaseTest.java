package tests;

import manager.PageFactoryManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;
import pages.apppages.HomePage;
import pages.apppages.LogInPage;
import pages.apppages.UserProfilePage;

import static io.github.bonigarcia.wdm.WebDriverManager.chromedriver;

public class BaseTest {

    protected static final long DEFAULT_TIMEOUT = 10;

    protected static final String HOME_URL = "https://epicentrk.ua/";
    protected WebDriver driver;
    protected PageFactoryManager pageFactoryManager;
    protected HomePage homePage;
    protected LogInPage logInPage;
    protected UserProfilePage userProfilePage;

    @BeforeTest
    public void testsSetUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        options.addArguments("--disable-notifications");
        chromedriver().setup();
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        pageFactoryManager = new PageFactoryManager(driver);
        homePage = new HomePage(driver);
        logInPage = new LogInPage(driver);
        userProfilePage = new UserProfilePage(driver);
    }

    @AfterTest
    public void tearDown() {
        driver.close();
    }

}
