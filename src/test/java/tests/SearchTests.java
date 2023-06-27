package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.apppages.HomePage;
import pages.apppages.ProductPage;
import pages.apppages.SearchResultsPage;

public class SearchTests extends BaseTest {

    private HomePage homePage;
    private ProductPage productPage;
    private SearchResultsPage searchResultsPage;

    @Override
    @BeforeTest
    public void testsSetUp() {
        super.testsSetUp();
        homePage = pageFactoryManager.getHomePage();
        productPage = pageFactoryManager.getProductPage();
        searchResultsPage = pageFactoryManager.getSearchResultsPage();
    }



    @DataProvider(name = "searchUsingEightDigitsCode")
    public static Object[][] searchUsingEightDigitsCodeDataProvider() {
        return new Object[][] {
                {
                        "10109711", "Диск Sturm для тримера"
                }
        };
    }

    @DataProvider(name = "searchForProductsApplyingTwoFilters")
    public static Object[][] searchForProductsApplyingTwoFiltersDataProvider() {
        return new Object[][] {
                {
                        "герметик", "гідроізоляційний", "для сантехніки", 0
                }
        };
    }

    @DataProvider(name = "searchForProductsApplyingTwoFiltersAndThenRemovingOne")
    public static Object[][] searchForProductsApplyingTwoFiltersAndThenRemovingOneDataProvider() {
        return new Object[][] {
                {
                        "клей для плитки", "підлога", "для ванної кімнати", 0
                }
        };
    }

    @Test(dataProvider = "searchUsingEightDigitsCode", description = "9. Перевірка пошуку за 8-значним кодом товару")
    public void searchUsingEightDigitsCodeTest(String eightDigitCode, String testProductName) {
        homePage.openHomePage(HOME_URL);
        homePage.enterProductNameInSearchInputField(eightDigitCode);
        Assert.assertTrue(productPage.getProductName().contains(testProductName));

    }

    @Test(dataProvider = "searchForProductsApplyingTwoFilters", description = "10. Перевірка додавання фільтрів при пошуку товару")
    public void searchForProductsApplyingTwoFiltersTest(String productName, String firstFilter, String secondFilter,
                                                        int foundProduct) {
        homePage.openHomePage(HOME_URL);
        homePage.enterProductNameInSearchInputField(productName);
        searchResultsPage.chooseFilterCheckbox(firstFilter);
        searchResultsPage.chooseFilterCheckbox(secondFilter);
        searchResultsPage.chooseSearchResultProduct(foundProduct);
        productPage.chooseProductCharacteristicsMenuSection();
        Assert.assertTrue(productPage.getProductDescription().contains(firstFilter));
        Assert.assertTrue(productPage.getProductDescription().contains(secondFilter));
    }

    @Test(dataProvider = "searchForProductsApplyingTwoFiltersAndThenRemovingOne",
            description = "11. Перевірка додавання і очищення фільтрів при пошуку товару")
    public void searchForProductsApplyingTwoFiltersAndThenRemovingOneTest(String productName, String firstFilter,
                                                                          String secondFilter, int foundProduct) {
        homePage.openHomePage(HOME_URL);
        homePage.enterProductNameInSearchInputField(productName);
        searchResultsPage.chooseFilterCheckbox(firstFilter);
        searchResultsPage.chooseFilterCheckbox(secondFilter);
        searchResultsPage.removeAppliedFilter(firstFilter);
        searchResultsPage.chooseSearchResultProduct(foundProduct);
        productPage.chooseProductCharacteristicsMenuSection();
        if (productPage.getProductDescription().contains(firstFilter)) {
            Assert.assertTrue(productPage.getProductDescription().contains(secondFilter));
        } else {
            SoftAssert softAssert = new SoftAssert();
            softAssert.assertTrue(productPage.getProductDescription().contains(secondFilter));
            softAssert.assertAll();
        }
    }


}
