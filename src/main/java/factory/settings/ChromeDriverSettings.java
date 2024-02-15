package factory.settings;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.AbstractDriverOptions;

public class ChromeDriverSettings implements IDriverSettings{

    @Override
    public AbstractDriverOptions settings() {
        ChromeOptions chromeOptions = new ChromeOptions();

        chromeOptions.addArguments("--no-sanbox");
        chromeOptions.addArguments("--no-first-run");

        return chromeOptions;
    }

}