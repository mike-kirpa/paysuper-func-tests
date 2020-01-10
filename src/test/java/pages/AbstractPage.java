package pages;

import helpers.Locators;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractPage {
    protected WebDriver driver;
    protected WebDriverWait webDriverWait;

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
        webDriverWait = new WebDriverWait(driver, 15);
    }

    void waitForPageLoad(String Locatorname) {
        WebDriverWait webDriverWait = new WebDriverWait(this.driver, 10);
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(Locators.get(Locatorname)));
    }
}
