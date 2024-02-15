package factory;

import exeption.BrowserNotSupportedExeption;
import factory.settings.IDriverSettings;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverFactory {
    private String browserName = System.getProperty("browser.name");
    private IDriverSettings driverSettings;

    public DriverFactory(IDriverSettings driverSettings) {
        this.driverSettings = driverSettings;
    }

    public WebDriver create() {
        browserName = browserName.toLowerCase();
        IDriverSettings settings= null;

        switch (browserName){
            case "chrome": {
                return new ChromeDriver((ChromeOptions) driverSettings.settings());
            }
        }
        throw new BrowserNotSupportedExeption(browserName);
    }
}
