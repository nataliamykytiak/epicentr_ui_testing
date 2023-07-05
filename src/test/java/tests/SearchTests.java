package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.apppages.HomePage;
import pages.apppages.ProductPage;
import pages.apppages.SearchResultsPage;

import java.util.Collections;
import java.util.Comparator;

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
                },
                {
                         "80389447", "Стіл обідній Boston"
                },
                {
                         "51625945", "Сковорода Grand Chef 28 см"
                },
                {
                         "80384090", "Диван прямий Кедр Торонто блакитний"
                },
                {
                         "80922041", "Кросівки Adidas"
                }
        };
    }

    @DataProvider(name = "searchForProductsApplyingTwoFilters")
    public static Object[][] searchForProductsApplyingTwoFiltersDataProvider() {
        return new Object[][] {
                {
                        "герметик", "гідроізоляційний", "для сантехніки", 0
                },
                {
                        "постільна білизна комплект", "двоспальний", "бавовна", 5
                },
                {
                        "змішувач для душу", "настінний", "з термостатом", 14
                },
                {
                        "горщик для квітів", "для бонсай", "овальний", 5
                },
                {
                        "стелаж", "метал", "багатоцільове", 18
                }
        };
    }

    @DataProvider(name = "searchForProductsApplyingTwoFiltersAndThenRemovingOne")
    public static Object[][] searchForProductsApplyingTwoFiltersAndThenRemovingOneDataProvider() {
        return new Object[][] {
                {
                        "клей для плитки", "підлога", "для ванної кімнати", 0
                },
                {
                        "папір офісний", "А4", "для принтера/копіра", 0
                },
                {
                        "круг відрізний", "по металу", "125", 2
                },
                {
                        "штучна трава", "з коротким ворсом", "2", 7
                },
                {
                        "пральний порошок", "для машинного прання", "антибактеріальний", 7
                }
        };
    }

    @DataProvider(name = "searchForProductsApplyingSortingByPriceAscendingThenDescending")
    public static Object[][] searchForProductsApplyingSortingByPriceAscendingThenDescendingDataProvider() {
        return new Object[][] {
                {
                        "ліхтар налобний"
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
        System.out.println(productPage.getProductDescription());
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
        System.out.println(productPage.getProductDescription());
        if (productPage.getProductDescription().contains(firstFilter)) {
            Assert.assertTrue(productPage.getProductDescription().contains(secondFilter));
        } else {
            SoftAssert softAssert = new SoftAssert();
            softAssert.assertTrue(productPage.getProductDescription().contains(secondFilter));
            softAssert.assertAll();
        }
    }

    @Test(dataProvider = "searchForProductsApplyingSortingByPriceAscendingThenDescending",
            description = "12. Перевірка сортування по ціні при пошуку товару")
    public void searchForProductsApplyingSortingByPriceAscendingThenDescending(String productName) {
        homePage.openHomePage(HOME_URL);
        homePage.enterProductNameInSearchInputField(productName);
        searchResultsPage.clickFilterButtonSortAscending();
        Assert.assertEquals(searchResultsPage.sortProductPricesInAscendingOrder(searchResultsPage.getSearchResultsListPrices()),
                            searchResultsPage.getSearchResultsListPrices());
        searchResultsPage.clickFilterButtonSortDescending();
        Assert.assertEquals(searchResultsPage.sortProductPricesInDescendingOrder(searchResultsPage.getSearchResultsListPrices()),
                searchResultsPage.getSearchResultsListPrices());
    }


}
