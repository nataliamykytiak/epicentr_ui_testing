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
    private ActionsPage actionsPage;

    @Override
    @BeforeTest
    public void testsSetUp() {
        super.testsSetUp();
        homePage = pageFactoryManager.getHomePage();
        actionsPage = pageFactoryManager.getActionsPage();
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

    @DataProvider(name = "checkThatActionBannerLeadsToCorrectActionPage")
    public static Object[][] checkThatActionBannerLeadsToCorrectActionPageDataProvider() {
        return new Object[][] {
                {
                        4
                },
        };
    }

    @Test(dataProvider = "checkCatalogSectionsTitles", description = "4. Перевірка розділів в каталозі меню")
    public void checkCatalogSectionsTitlesTest(List<String> expectedTitles){
        homePage.openHomePage(HOME_URL);
        homePage.clickCatalogMenuBurger();
        List<String> actualTitles = homePage.getCatalogMenuBurgerSectionsListTitles();
        System.out.println(homePage.getCatalogMenuBurgerSectionsListTitles());
        Assert.assertEquals(actualTitles, expectedTitles);
    }

//not a stable test, depends on current actions on the website
    @Test(dataProvider = "checkThatActionBannerLeadsToCorrectActionPage", description = "14. Перевірка банера і прив'язки до нього акцій")
    public void checkThatActionBannerLeadsToCorrectActionPageTest(int listNumber){
        homePage.openHomePage(HOME_URL);
        String homePageBannerImage = homePage.getFormattedImageAddress(listNumber);
        System.out.println(homePageBannerImage);
        homePage.chooseActionsBanner(listNumber);
        String actionsPageBannerImage = actionsPage.getFormattedImageAddress();
        System.out.println(actionsPageBannerImage);
        Assert.assertEquals(homePageBannerImage, actionsPageBannerImage);
    }


}
