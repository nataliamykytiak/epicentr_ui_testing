package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.apppages.CartPage;
import pages.apppages.HomePage;
import pages.apppages.SearchResultsPage;

public class AddingProductsToCartTests extends BaseTest {

    private HomePage homePage;
    private SearchResultsPage searchResultsPage;
    private CartPage cartPage;

    @Override
    @BeforeTest
    public void testsSetUp() {
        super.testsSetUp();
        homePage = pageFactoryManager.getHomePage();
        searchResultsPage = pageFactoryManager.getSearchResultsPage();
        cartPage = pageFactoryManager.getCartPage();
    }

    @DataProvider(name = "checkUserCanAddOneProductToCart")
    public static Object[][] checkUserCanAddOneProductToCartDataProvider() {
        return new Object[][] {
                {
                        "0", "сукня літня", 0, "ВАШ КОШИК ТОВАРІВ", "1"
                }
        };
    }

    @Test(dataProvider = "checkUserCanAddOneProductToCart")
    public void checkUserCanAddOneProductToCartTest(String emptyCartValue, String productName, int productNumber,
                                                    String cartHeadline, String afterAddingCartValue) {
        homePage.openHomePage(HOME_URL);
        Assert.assertEquals(homePage.getCartCounterValue(), emptyCartValue);
        homePage.enterProductNameInSearchInputField(productName);
        searchResultsPage.pressBuyButtonToAddProductToCart(productNumber);
        Assert.assertEquals(cartPage.getCartPageName(), cartHeadline);
        cartPage.pressContinueShoppingButton();
        Assert.assertEquals(searchResultsPage.getCartCounterValue(), afterAddingCartValue);

    }
}
