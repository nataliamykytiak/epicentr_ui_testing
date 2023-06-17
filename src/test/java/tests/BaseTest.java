package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import manager.PageFactoryManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;

public abstract class BaseTest {

    protected static final String HOME_URL = "https://epicentrk.ua/";
    protected WebDriver driver;
    protected PageFactoryManager pageFactoryManager;



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
    };

    @AfterTest
    public void tearDown() {
        driver.close();
        driver.quit();
    }


}
