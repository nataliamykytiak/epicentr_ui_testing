package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.apppages.*;

public class LogInTests extends BaseTest {

    private HomePage homePage;
    private LogInPage logInPage;
    private UserProfilePage userProfilePage;

    @Override
    public void testsSetUp() {
        super.testsSetUp();
        homePage = pageFactoryManager.getHomePage();
        logInPage = pageFactoryManager.getLogInPage();
        userProfilePage = pageFactoryManager.getUserProfilePage();
    }


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
    public void loginWithValidCredentialsAsARegisteredUserTest(final String phoneNumber, String userPassword,
                                                                 String userName, String logInButtonName) {
        homePage.openHomePage(HOME_URL);
        homePage.clickLogInIcon();
        logInPage.enterPhoneNumber(phoneNumber);
        logInPage.enterUserPassword(userPassword);
        logInPage.clickLogInButton();
        Assert.assertEquals(homePage.getLogInIconText(), userName);
        homePage.clickLogInIcon();
        userProfilePage.clickLogOutButton();
        Assert.assertEquals(homePage.getLogInIconText(), logInButtonName);
    }

    @Test(dataProvider = "logInNotRegisteredUser")
    public void loginAsNotRegisteredUserTest(final String phoneNumber, String userPassword, String warning) {
        homePage.openHomePage(HOME_URL);
        homePage.clickLogInIcon();
        logInPage.enterPhoneNumber(phoneNumber);
        logInPage.enterUserPassword(userPassword);
        logInPage.clickLogInButton();
        logInPage.waitForPageLoadComplete();
        Assert.assertEquals(logInPage.getLogInWarning(), warning);
    }









}