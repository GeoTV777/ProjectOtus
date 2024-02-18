package pages;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.IOException;
import java.util.List;

public class LessonPage extends AbsBasePage {

    private CatalogPage catalogPage;
    public LessonPage(WebDriver driver, String lessonsPath) {
        super(driver, String.format("/lessons/%s", lessonsPath));
        this.catalogPage = new CatalogPage(driver);
    }

    // проверка заголовка по индексу LessonPage
    public void checkHeaderLessonByIndex(int index, String expectedHeader) throws IOException {
        Document dom =catalogPage.getDomPage(index);
        Element headerPageElement = dom.selectFirst("h1");
        Assertions.assertEquals(expectedHeader, headerPageElement.text(), "");
    }
    //добавить ошибку!!

    //  проверка остальных заголовков по индексу LessonPage
    public void checkDescriptionLessonByIndex(int index) throws IOException{
//        Elements elements = getDomPage(index).selectXpath("//div[contains(@class,'dZDxRw')]");
//        if (elements.isEmpty()){
//            elements = getDomPage(index).selectXpath("//h1/following-sibling::div/p[text]");
//        }
        Element headerPageElement = catalogPage.getDomPage(index).selectXpath("//div[contains(@class,'dZDxRw')]").get(0);
        Assertions.assertFalse(headerPageElement.text().isEmpty(),"");
    }
    //добавить ошибку!!
    //  проверка продолжительности курса из LessonPage
    public void checkLessonDuration(int index, String expectedDuration)throws IOException{
        Element headerPageElement = catalogPage.getDomPage(index)
                .selectXpath("//div/following-sibling::p[contains(text(),'месяц')]").get(0);
        Assertions.assertEquals(expectedDuration.replaceAll("^.*?·\\s*",""), headerPageElement.text(),"");

        //добавить ошибку!!
    }
    // проверка формата обучения LessonPage
    public void checkLessonFormat(int index, String format) throws IOException {
        Element formatLessonElement = catalogPage.getDomPage(index)
                .selectXpath(String.format("//p[contains(text(),'%s')]",format)).get(0);
        Assertions.assertFalse(formatLessonElement.text().isEmpty(),"");
    }
    //добавить ошибку!!

}
