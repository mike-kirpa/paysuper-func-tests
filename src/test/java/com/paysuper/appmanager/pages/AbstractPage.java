package com.paysuper.appmanager.pages;

import com.paysuper.appmanager.helpers.Locators;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractPage {
    protected WebDriver driver;
    protected WebDriverWait webDriverWait;

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
        webDriverWait = new WebDriverWait(this.driver, 15);
    }

    protected void waitForElementLoad(String LocatorName) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, 10);
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(Locators.get(LocatorName)));
    }

    public boolean isElementPresent(String LocatorName){
        return driver.findElements(Locators.get(LocatorName)).size() > 0;
    }

    public String getTextOfElement(String LocatorName){
        return driver.findElement(Locators.get(LocatorName)).getText();
    }


}
