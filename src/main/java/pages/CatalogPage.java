package pages;

import org.openqa.selenium.WebDriver;

public class CatalogPage extends AbsBasePage{

    public CatalogPage(WebDriver driver) {
        super(driver, "/catalog/courses");
    }
}
