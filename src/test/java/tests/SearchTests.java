package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.apppages.HomePage;
import pages.apppages.ProductPage;

public class SearchTests extends BaseTest {

    private HomePage homePage;
    private ProductPage productPage;

    @Override
    @BeforeTest
    public void testsSetUp() {
        super.testsSetUp();
        homePage = pageFactoryManager.getHomePage();
        productPage = pageFactoryManager.getProductPage();
    }



    @DataProvider(name = "searchUsingEightDigitsCode")
    public static Object[][] searchUsingEightDigitsCodeDataProvider() {
        return new Object[][] {
                {
                        "10109711", "Диск Sturm для тримера"
                }
        };
    }

    @Test(dataProvider = "searchUsingEightDigitsCode")
    public void searchUsingEightDigitsCodeTest(String eightDigitCode, String testProductName) {
        homePage.openHomePage(HOME_URL);
        homePage.enterProductNameInSearchInputField(eightDigitCode);
        Assert.assertTrue(productPage.getProductName().contains(testProductName));

    }


}
