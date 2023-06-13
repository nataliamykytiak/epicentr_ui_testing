package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import manager.PageFactoryManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;
import pages.apppages.*;

public class BaseTest {



    protected static final String HOME_URL = "https://epicentrk.ua/";
    protected WebDriver driver;
    protected PageFactoryManager pageFactoryManager;
    protected HomePage homePage;
    protected LogInPage logInPage;
    protected UserProfilePage userProfilePage;

    protected SearchResultsPage searchResultsPage;
    protected ProductPage productPage;
    protected CompareItemsPage compareItemsPage;
    protected WishListPage wishListPage;



    @BeforeTest
    public void testsSetUp() {
        ChromeOptions options = new ChromeOptions();
//        options.addArguments("headless=new");
//        options.addArguments("--disable-popup-blocking");
//        options.addArguments("–disable-geolocation");
        options.addArguments("--reset-geolocation-permissions");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        pageFactoryManager = new PageFactoryManager(driver);
        homePage = new HomePage(driver);
        logInPage = new LogInPage(driver);
        userProfilePage = new UserProfilePage(driver);
        productPage = new ProductPage(driver);
        searchResultsPage = new SearchResultsPage(driver);
        compareItemsPage = new CompareItemsPage(driver);
        wishListPage= new WishListPage(driver);
    }

    @AfterTest
    public void tearDown() {
        driver.close();
        driver.quit();
    }


}
