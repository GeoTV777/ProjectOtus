package pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CalendarEventsPage extends AbsBasePage{

    public CalendarEventsPage(WebDriver driver) {
        super(driver, "/events/near/");
    }
    @FindBy(css = ".dod_new-event")
    private List<WebElement> eventTiles;
//    @FindBy(css = ".dod_new-event__icon~.dod_new-event__date-text")
    @FindBy(css = ".dod_new-event__icon~.dod_new-event__date-text")
    private List<WebElement>dateEvents;

    public CalendarEventsPage checkEventTilesShouldBeSameAs() {
        Assertions.assertTrue(waitTools.waitForCondition((ExpectedConditions.visibilityOfAllElements(eventTiles))));

        return this;
    }

    public CalendarEventsPage  checkStartEventDate() {
//        for(WebElement dateEvent: dateEvents) {
//            LocalDate currentDate = LocalDate.now();
//            Pattern pattern = Pattern.compile("\\d+\\s+[а-яА-Я]+\\s+\\d{4}");
//            String dateEventStr = dateEvent.getText();
//            Matcher matcher = pattern.matcher(dateEventStr);
//            if(!matcher.find()) {
//                dateEventStr += String.format(" %d", currentDate.getYear());
//            }
//            LocalDate eventDate = LocalDate.parse(dateEventStr,
//                    DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale.of("ru")));
//
//            Assertions.assertTrue(eventDate.isAfter(currentDate) || eventDate.isEqual(currentDate),
//                    "The event date is not as expected or has already passed");
//        }
//        return this;
        for(WebElement dateEvent: dateEvents) {
            LocalDate currentDate = LocalDate.now();
            String dateEventStr = dateEvent.getText() + String.format(" %d", currentDate.getYear());
            LocalDate eventDate = LocalDate.parse(dateEventStr,DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale.of("ru")));

            Assertions.assertTrue(eventDate.isAfter(currentDate) || eventDate.isEqual(currentDate),
                    "The event date is not as expected or has already passed");
        }
        return this;
    }
}
