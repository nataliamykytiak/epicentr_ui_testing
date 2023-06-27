package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
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

    @DataProvider(name = "searchUsingFilters")
    public static Object[][] searchUsingFiltersDataProvider() {
        return new Object[][] {
                {
                        "герметик", "гідроізоляційний", "для сантехніки", 0
                }
        };
    }

    @Test(dataProvider = "searchUsingEightDigitsCode", description = "9. Перевірка пошуку за 8-значним кодом товару")
    public void searchUsingEightDigitsCodeTest(String eightDigitCode, String testProductName) {
        homePage.openHomePage(HOME_URL);
        homePage.enterProductNameInSearchInputField(eightDigitCode);
        Assert.assertTrue(productPage.getProductName().contains(testProductName));

    }

    @Test(dataProvider = "searchUsingFilters", description = "10. Перевірка додавання фільтрів при пошуку товару")
    public void searchUsingFiltersTest(String productName, String firstFilter, String secondFilter, int foundProduct) {
        homePage.openHomePage(HOME_URL);
        homePage.enterProductNameInSearchInputField(productName);
        searchResultsPage.chooseFilterCheckbox(firstFilter);
        searchResultsPage.chooseFilterCheckbox(secondFilter);
        searchResultsPage.chooseSearchResultProduct(foundProduct);
        productPage.chooseProductCharacteristicsMenuSection();
        Assert.assertTrue(productPage.getProductDescription().contains(firstFilter));
        Assert.assertTrue(productPage.getProductDescription().contains(secondFilter));





    }


}
