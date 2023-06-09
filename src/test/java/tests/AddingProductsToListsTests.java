package tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.apppages.*;

public class AddingProductsToListsTests extends BaseTest {

    private HomePage homePage;
    private SearchResultsPage searchResultsPage;
    private CompareItemsPage compareItemsPage;
    private LogInPage logInPage;
    private WishListPage wishListPage;
    private UserProfilePage userProfilePage;

    @Override
    @BeforeTest
    public void testsSetUp() {
        super.testsSetUp();
        homePage = pageFactoryManager.getHomePage();
        logInPage = pageFactoryManager.getLogInPage();
        searchResultsPage = pageFactoryManager.getSearchResultsPage();
        compareItemsPage = pageFactoryManager.getCompareItemsPage();
        wishListPage = pageFactoryManager.getWishListPage();
        userProfilePage = pageFactoryManager.getUserProfilePage();
    }

    @AfterMethod(description = "6. Перевірка додавання товарів до обраних, коли користувач авторизований")
    public void logOut() {
        userProfilePage.clickLogOutButton();
    }




    @DataProvider(name = "checkTwoProductsAddedToCompareList")
    public static Object[][] checkTwoProductsAddedToCompareListDataProvider() {
        return new Object[][] {
                {
                        "граблі віялові", 0, 1, "Вибачте, але Ви ще нічого не додали в порівняння"
                },
                {
                        "засіб для миття підлоги", 5, 1, "Вибачте, але Ви ще нічого не додали в порівняння"
                },
                {
                        "мішок поліпропіленовий", 6, 14, "Вибачте, але Ви ще нічого не додали в порівняння"
                },
                {
                        "насос дренажний", 11, 6, "Вибачте, але Ви ще нічого не додали в порівняння"
                },
                {
                        "валіза на колесах", 13, 14, "Вибачте, але Ви ще нічого не додали в порівняння"
                },
        };
    }

    @DataProvider(name = "checkAddingProductToWishListFailsWhenUserIsNotAuthorized")
    public static Object[][] checkAddingProductToWishListFailsWhenUserIsNotAuthorizedDataProvider() {
        return new Object[][] {
                {
                        "моторне мастило", 0, "Ласкаво просимо до epicentrk.ua!"
                },
                {
                        "плитка гіпсова", 17, "Ласкаво просимо до epicentrk.ua!"
                },
                {
                        "човен надувний", 3, "Ласкаво просимо до epicentrk.ua!"
                },
                {
                        "килимок для йоги", 3, "Ласкаво просимо до epicentrk.ua!"
                },
                {
                        "телевізор", 21, "Ласкаво просимо до epicentrk.ua!"
                },
        };
    }

    @DataProvider(name = "checkAddingProductToWishListWhenUserIsAuthorized")
    public static Object[][] checkAddingProductToWishListWhenUserIsAuthorizedDataProvider() {
        return new Object[][] {
                {
                        "938702483", "Nataliia.1", "корм для собак", 0, 1, 2, "Вибачте, але Ви ще нічого не додали до «Бажань»"
                },
                {
                        "938702483", "Nataliia.1", "бойлер", 13, 8, 0, "Вибачте, але Ви ще нічого не додали до «Бажань»"
                },
                {
                        "938702483", "Nataliia.1", "шланг для поливу", 19, 20, 13, "Вибачте, але Ви ще нічого не додали до «Бажань»"
                },
                {
                        "938702483", "Nataliia.1", "вентилятор", 8, 15, 13, "Вибачте, але Ви ще нічого не додали до «Бажань»"
                },
                {
                        "938702483", "Nataliia.1", "піна монтажна", 3, 6, 16, "Вибачте, але Ви ще нічого не додали до «Бажань»"
                },

        };
    }

    @Test(dataProvider = "checkTwoProductsAddedToCompareList", description = "5. Перевірка додавання товарів до порівняння")
    public void checkTwoProductsAddedToCompareListTest(String productName, int firstProduct, int secondProduct,
                                                       String emptyCompareListMessage){
        homePage.openHomePage(HOME_URL);
        homePage.enterProductNameInSearchInputField(productName);
        searchResultsPage.waitForPageLoadComplete();
        searchResultsPage.addProductToCompareList(firstProduct);
        searchResultsPage.addProductToCompareList(secondProduct);
        searchResultsPage.goToCompareList();
        Assert.assertEquals(compareItemsPage.getNumberOfItemsAddedToCompareList(), 2);
        compareItemsPage.removeOneItemFromCompareList();
        Assert.assertEquals(compareItemsPage.getNumberOfItemsAddedToCompareList(), 1);
        compareItemsPage.removeOneItemFromCompareList();
        Assert.assertTrue(compareItemsPage.getSorryYouHaveNotAddedMessageText().contains(emptyCompareListMessage));
    }

    @Test(dataProvider = "checkAddingProductToWishListFailsWhenUserIsNotAuthorized",
            description = "7. Перевірка додавання товарів до обраних, коли користувач не авторизований")
    public void checkAddingProductToWishListFailsWhenUserIsNotAuthorizedTest(String productName, int product,
                                                       String welcomeMessage){
        homePage.openHomePage(HOME_URL);
        homePage.enterProductNameInSearchInputField(productName);
        searchResultsPage.waitForPageLoadComplete();
        searchResultsPage.addProductToWishList(product);
        Assert.assertEquals(logInPage.getLogInWelcomeText(), welcomeMessage);
    }

    @Test(dataProvider = "checkAddingProductToWishListWhenUserIsAuthorized",
            description = "6. Перевірка додавання товарів до обраних, коли користувач авторизований")
    public void checkAddingProductToWishListWhenUserIsAuthorizedTest(String phoneNumber, String userPassword,
                                                                     String productName, int firstProduct,
                                                                     int secondProduct, int thirdProduct,
                                                                     String emptyWishListMessage){
        homePage.openHomePage(HOME_URL);
        homePage.clickLogInIcon();
        logInPage.enterPhoneNumber(phoneNumber);
        logInPage.enterUserPassword(userPassword);
        logInPage.clickLogInButton();
        homePage.enterProductNameInSearchInputField(productName);
        searchResultsPage.waitForPageLoadComplete();
        searchResultsPage.addProductToWishList(firstProduct);
        searchResultsPage.addProductToWishList(secondProduct);
        searchResultsPage.addProductToWishList(thirdProduct);
        searchResultsPage.goToWishList();
        Assert.assertEquals(wishListPage.getNumberOfItemsAddedToWishList(), 3);
        wishListPage.removeOneItemFromWishList();
        Assert.assertEquals(wishListPage.getNumberOfItemsAddedToWishList(), 2);
        wishListPage.removeOneItemFromWishList();
        Assert.assertEquals(wishListPage.getNumberOfItemsAddedToWishList(), 1);
        wishListPage.removeOneItemFromWishList();
        Assert.assertTrue(wishListPage.getSorryYouHaveNotAddedMessageText().contains(emptyWishListMessage));
    }


}
