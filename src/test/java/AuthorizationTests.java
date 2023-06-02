import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import tests.BaseTest;

public class AuthorizationTests extends BaseTest {

    @DataProvider(name = "logInRegisteredUser")
    public static Object[][] logInRegisteredUserDataProvider() {
        return new Object[][] {
                {
                    "938702483", "Nataliia.1", "Наталя", "Увійти"
                }
        };
    }

    @DataProvider(name = "logInNotRegisteredUser")
    public static Object[][] logInNotRegisteredUserDataProvider() {
        return new Object[][] {
                {
                        "93870248", "Nataliia.1", "Невiрний логiн або пароль."
                }
        };
    }


    @Test(dataProvider = "logInRegisteredUser")
    public void loginWithValidCredentialsAsARegisteredUser(final String phoneNumber, String userPassword,
                                                                 String userName, String logInButtonName) {
        homePage.openHomePage(HOME_URL);
        homePage.waitForPageLoadComplete(DEFAULT_TIMEOUT);
        homePage.clickLogInIcon();
        logInPage.enterPhoneNumber(phoneNumber);
        logInPage.enterUserPassword(userPassword);
        logInPage.clickLogInButton();
        homePage.waitForPageLoadComplete(DEFAULT_TIMEOUT);
        Assert.assertEquals(homePage.getLogInIconText(), userName);
        homePage.clickLogInIcon();
        userProfilePage.clicklogOutButton();
        Assert.assertEquals(homePage.getLogInIconText(), logInButtonName);
    }

    @Test(dataProvider = "logInNotRegisteredUser")
    public void loginAsNotRegisteredUser(final String phoneNumber, String userPassword, String warning) {
        homePage.openHomePage(HOME_URL);
        homePage.waitForPageLoadComplete(DEFAULT_TIMEOUT);
        homePage.clickLogInIcon();
        logInPage.enterPhoneNumber(phoneNumber);
        logInPage.enterUserPassword(userPassword);
        logInPage.clickLogInButton();
        logInPage.waitForPageLoadComplete(DEFAULT_TIMEOUT);
        Assert.assertEquals(logInPage.getLogInWarning(), warning);
    }









}