package stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import manager.PageFactoryManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.apppages.HomePage;

import static io.github.bonigarcia.wdm.WebDriverManager.chromedriver;

public class StepDefinitions {

    WebDriver driver;
    PageFactoryManager pageFactoryManager;
    HomePage homePage;

    @Before
    public void testsSetUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        chromedriver().setup();
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        pageFactoryManager = new PageFactoryManager(driver);
    }

    @After
    public void tearDown() {
        driver.close();
    }

    @And("User opens {string} page")
    public void openPage(final String url) {
        homePage = pageFactoryManager.getHomePage();
        homePage.openHomePage(url);
    }


}
