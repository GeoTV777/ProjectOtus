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

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CorseTilesTest {

    private WebDriver driver;
    private Logger logger = (Logger) LogManager.getLogger("Autotest");


    @BeforeEach
        public void init() throws BrowserNotSupportedExeption{
        ChromeDriverSettings driverSettings = new ChromeDriverSettings();
        this.driver = new DriverFactory(driverSettings).create();
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
        List<String> queryParams = new ArrayList<>();
        queryParams.add(String.format("categories=%s", LessonsCategoryData.TESTING.name().toLowerCase(Locale.ROOT)));

        CatalogPage catalogPage = new CatalogPage(driver);
        catalogPage.open(queryParams);
        catalogPage.lessonTilesNumberShouldBeSameAs(10);
    }

}
