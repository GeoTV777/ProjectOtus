package pages;

import org.checkerframework.checker.units.qual.A;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class CatalogPage extends AbsBasePage{

    public CatalogPage(WebDriver driver) {
        super(driver, "/catalog/courses");
    }

    @FindBy(css = "div.bwGwUO a")
    private List<WebElement> lessonsTiles;

    @FindBy(xpath ="//section[contains(@class, 'sc-o4bnil-0') and contains(@class, 'riKpM')]//div[contains(text(),'месяц')]")
    private List<WebElement> lessonDuration;

    public void lessonTilesNumberShouldBeSameAs(int number) {
        Assertions.assertEquals(
                number,
                lessonsTiles.size(),
                String.format("Numbers jf lessons tiles should be %d", number)
        );
    }

    public void clickRandomLessonsTile() {
        faker.options().nextElement(lessonsTiles).click();
    }

    public int getTilesNumbers() {
        return lessonsTiles.size();
    }

    public String getLessonNameByIndex(int index) {
        return lessonsTiles.get(--index).findElement(By.xpath(".//h6")).getText();
    }
    public String getLessonDuration(int index) {
        return lessonDuration.get(--index).getText();
    }

    private Document getDomPage(int index) throws IOException {
        String url = lessonsTiles.get(--index).getAttribute("href");
       return Jsoup.connect(url).get();
    }

    public void checkHeaderLessonByIndex(int index, String expectedHeader) throws IOException {

        Document dom = getDomPage(index);
        Element headerPageElement = dom.selectFirst("h1");
        Assertions.assertEquals(expectedHeader, headerPageElement.text(), "");
    }
        //добавить ошибку!!
    public void checkDescriptionLessonByIndex(int index) throws IOException{
        Element headerPageElement = getDomPage(index).selectXpath("//div[contains(@class,'dZDxRw')]").get(0);

        Assertions.assertFalse(headerPageElement.text().isEmpty(),"");
    }
        //добавить ошибку!!
    public void checkLessonDuration(int index, String expectedDuration)throws IOException{
        Element headerPageElement = getDomPage(index)
                .selectXpath("//div/following-sibling::p[contains(text(),'месяц')]").get(0);
        Assertions.assertEquals(expectedDuration, headerPageElement.text(),"");

        //добавить ошибку!!
    }

    public void checkLessonFormat(int index, String format) throws IOException {
        Element formatLessonElement = getDomPage(index)
                .selectXpath(String.format("//p[contains(text(),'%s')]",format)).get(0);
        Assertions.assertFalse(formatLessonElement.text().isEmpty(),"");
    }
         //добавить ошибку!!
}
