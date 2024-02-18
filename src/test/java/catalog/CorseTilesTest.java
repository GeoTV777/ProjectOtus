package catalog;

import data.catalog.LessonsCategoryData;
import exeption.BrowserNotSupportedExeption;
import factory.DriverFactory;
import factory.settings.ChromeDriverSettings;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pages.CatalogPage;
import pages.LessonPage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CorseTilesTest {

    private WebDriver driver;
    private CatalogPage catalogPage =null;
    private Logger logger = (Logger) LogManager.getLogger("Autotest");

    @BeforeEach
        public void init() throws BrowserNotSupportedExeption{
        ChromeDriverSettings driverSettings = new ChromeDriverSettings();
        this.driver = new DriverFactory(driverSettings).create();

        List<String> queryParams = new ArrayList<>();
        queryParams.add(String.format("categories=%s", LessonsCategoryData.TESTING.name().toLowerCase(Locale.ROOT)));

        this.catalogPage = new CatalogPage(driver);
        catalogPage.open(queryParams);
    }

    @AfterEach
    public void driverStop() {
        if (driver != null) {
            driver.close();
            driver.quit();
            logger.info("Close browser");
        }
    }
    @Test
    public void catalogTilesNumbers() {
        catalogPage.lessonTilesNumberShouldBeSameAs(10);
    }

    @Test
    public void checkDataOnLessonPage()throws IOException {
        for(int i=1; i<catalogPage.getTilesNumbers(); i++){
            String expectedHeader = catalogPage.getLessonNameByIndex(i);
            String expectedLessonDuration = catalogPage.getLessonDuration(i);

            catalogPage.checkHeaderLessonByIndex(i,expectedHeader);
            catalogPage.checkDescriptionLessonByIndex(i);
            catalogPage.checkLessonDuration(i,expectedLessonDuration);
            catalogPage.checkLessonFormat(i,"Онлайн");
        }
        catalogPage.clickRandomLessonsTile();
        LessonPage lessonPage=new LessonPage(driver,"");
        //  и перенести методы касающиеся внутренности курса (карточки)  в класс LessonPage

    }

}
