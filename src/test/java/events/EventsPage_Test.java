package events;

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
import pages.CalendarEventsPage;
import pages.CatalogPage;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class EventsPage_Test {
    private WebDriver driver;
    private CalendarEventsPage calendarEventsPage;
    private Logger logger = (Logger) LogManager.getLogger("ProjectOtus");

    @BeforeEach
    public void init() throws BrowserNotSupportedExeption {
        ChromeDriverSettings driverSettings = new ChromeDriverSettings();
        this.driver = new DriverFactory(driverSettings).create();

        this.calendarEventsPage = new CalendarEventsPage(driver);
        calendarEventsPage.open();
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
    public void eventsTiles() {
        calendarEventsPage
                .checkEventTilesShouldBeSameAs()
                .checkStartEventDate();

    }



}
