package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.apppages.*;

import java.util.Arrays;
import java.util.List;

public class CatalogTests extends BaseTest{

    private HomePage homePage;

    @Override
    @BeforeTest
    public void testsSetUp() {
        super.testsSetUp();
        homePage = pageFactoryManager.getHomePage();
    }

    @DataProvider(name = "checkCatalogSectionsTitles")
    public static Object[][] checkCatalogSectionsTitlesDataProvider() {
        return new Object[][] {
                {
                        Arrays.asList("РЕМОНТ", "ПОКРИТТЯ ДЛЯ ПІДЛОГИ", "САНТЕХНІКА", "МЕБЛІ", "САД ГОРОД", "ЗООТОВАРИ (ЗООМАГАЗИН)",
                        "ОСВІТЛЕННЯ", "ДЛЯ ДОМУ", "ІНСТРУМЕНТИ", "ПОБУТОВА ТЕХНІКА", "ЕЛЕКТРОНІКА", "ДИТЯЧІ ТОВАРИ",
                        "КРАСА ТА ЗДОРОВ'Я", "ПРОДУКТИ", "ОДЯГ, ВЗУТТЯ, АКСЕСУАРИ", "СПОРТИВНІ ТОВАРИ", "ТУРИЗМ І МІЛІТАРІ",
                        "ПОБУТОВА ХІМІЯ", "ДЛЯ АВТО", "КАНЦТОВАРИ", "ТОВАРИ ДЛЯ БІЗНЕСУ", "АПТЕКА")
                }
        };
    }

    @Test(dataProvider = "checkCatalogSectionsTitles")
    public void checkCatalogSectionsTitlesTest(List<String> expectedTitles){
        homePage.openHomePage(HOME_URL);
        homePage.clickCatalogMenuBurger();
        List<String> actualTitles = homePage.getCatalogMenuBurgerSectionsListTitles();
        Assert.assertEquals(actualTitles, expectedTitles);

    }
}
