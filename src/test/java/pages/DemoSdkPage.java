package pages;

import org.openqa.selenium.WebDriver;

public class DemoSdkPage extends AbstractPage {
    public DemoSdkPage(WebDriver driver) {
        super(driver);
        waitForPageLoad("DemoSdkPage.BuyButton");
    }
}
