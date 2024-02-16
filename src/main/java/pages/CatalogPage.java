package pages;

import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CatalogPage extends AbsBasePage{

    public CatalogPage(WebDriver driver) {
        super(driver, "/catalog/courses");
    }

    @FindBy(css = "div.bwGwUO a")
    private List<WebElement> lessonsTiles;

    public void lessonTilesNumberShouldBeSameAs(int number) {
        Assertions.assertEquals(
                number,
                lessonsTiles.size(),
                String.format("Numbers jf lessons tiles should be %d", number)
        );

    }
}
