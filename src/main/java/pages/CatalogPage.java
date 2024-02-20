package pages;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.io.IOException;
import java.util.List;

public class CatalogPage extends AbsBasePage{

    public CatalogPage(WebDriver driver) {
        super(driver, "/catalog/courses");
    }

    @FindBy(css = "div.bwGwUO a")
    private List<WebElement> lessonsTiles;

    @FindBy(xpath ="//section[contains(@class, 'sc-o4bnil-0') and contains(@class, 'riKpM')]//div[contains(text(),'месяц')]")
    private List<WebElement> lessonDuration;

    // проверка того, что количество плиток должно быть 10
    public void lessonTilesNumberShouldBeSameAs(int number) {
        Assertions.assertEquals(
                number,
                lessonsTiles.size(),
                String.format("Numbers jf lessons tiles should be %d", number)
        );
    }
    // случайный клик по плитке лекций
    public void clickRandomLessonsTile() {
        faker.options().nextElement(lessonsTiles).click();
    }
    // получение номера плитки
    public int getTilesNumbers() {
        return lessonsTiles.size();
    }

    //  получение название лекций по индексу из плиток
    public String getLessonNameByIndex(int index) {
        return lessonsTiles.get(--index).findElement(By.xpath(".//h6")).getText();
    }
    // получение продолжительности курса из
    public String getLessonDuration(int index) {
        return lessonDuration.get(--index).getText();
    }
    //  данный метод выполняет запрос к указанной веб-странице и возвращает ее DOM-структуру в виде объекта `Document`.
    protected Document getDomPage(int index) throws IOException {
        String url = lessonsTiles.get(--index).getAttribute("href");
        return Jsoup.connect(url).get();
    }
}
