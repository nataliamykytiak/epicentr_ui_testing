package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LocationTests extends BaseTest{


    @DataProvider(name = "changeCurrentLocation")
    public static Object[][] changeCurrentLocationDataProvider() {
        return new Object[][] {
                {
                        "Київ", "Суми"
                }
        };
    }

    @Test(dataProvider = "changeCurrentLocation")
    public void changeCurrentLocationTest(String preselectedCity, String yourCity) throws InterruptedException {
        homePage.openHomePage(HOME_URL);
        Assert.assertEquals(homePage.getCurrentLocationIconText().toLowerCase(), preselectedCity.toLowerCase());
        homePage.clickCurrentLocationIcon();
        homePage.enterYourCity(yourCity);
        homePage.chooseYourCityFromTheList();
        homePage.clickChooseYourStoreButton();
        Assert.assertEquals(homePage.getCurrentLocationIconText().toLowerCase(), yourCity.toLowerCase());

    }


}
