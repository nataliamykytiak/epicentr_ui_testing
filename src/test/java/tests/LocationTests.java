package tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.apppages.*;

public class LocationTests extends BaseTest{

    private HomePage homePage;

    @Override
    @BeforeTest
    public void testsSetUp() {
        super.testsSetUp();
        homePage = pageFactoryManager.getHomePage();
    }

    @AfterMethod(description = "3. Перевірка зміни локації користувача")
    public void changeLocationBackToKyiv() {
        homePage.clickCurrentLocationIcon();
        homePage.enterYourCity("Київ");
        homePage.chooseYourCityFromTheList();
        homePage.clickChooseYourStoreButton();
    }

    @DataProvider(name = "changeCurrentLocation")
    public static Object[][] changeCurrentLocationDataProvider() {
        return new Object[][] {
                {
                        "Київ", "Суми"
                },
                {
                        "Київ", "Дніпро"
                },
                {
                        "Київ", "Херсон"
                },
                {
                        "Київ", "Одеса"
                },
                {
                        "Київ", "Ізюм"
                }
        };
    }

    @Test(dataProvider = "changeCurrentLocation", description = "3. Перевірка зміни локації користувача")
    public void changeCurrentLocationTest(String preselectedCity, String yourCity) {
        homePage.openHomePage(HOME_URL);
        Assert.assertEquals(homePage.getCurrentLocationIconText().toLowerCase(), preselectedCity.toLowerCase());
        homePage.clickCurrentLocationIcon();
        homePage.enterYourCity(yourCity);
        homePage.chooseYourCityFromTheList();
        homePage.clickChooseYourStoreButton();
        Assert.assertEquals(homePage.getCurrentLocationIconText().toLowerCase(), yourCity.toLowerCase());

    }


}
