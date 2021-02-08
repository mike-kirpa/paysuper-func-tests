package com.paysuper.appmanager.pages;

import com.paysuper.appmanager.helpers.DataGenerator;
import com.paysuper.appmanager.helpers.Locators;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class AbstractPage {
    protected WebDriver driver;
    protected WebDriverWait webDriverWait;

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
        webDriverWait = new WebDriverWait(this.driver, 15);
    }

    public void waitForElementLoad(String LocatorName) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, 15);
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(Locators.get(LocatorName)));
    }

    public void waitForElementLoad(By by) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, 15);
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public void waitForElementNotPresence(By by) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, 15);
        webDriverWait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }
    public void waitForElementNotPresence(WebElement element) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, 15);
        webDriverWait.until(ExpectedConditions.invisibilityOf(element));
    }

    public void waitForClickAbleElement(By by) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, 15);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(by));
    }

    public void waitForClickAbleElement(WebElement element) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, 15);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public boolean isElementPresent(String LocatorName){
        return driver.findElements(Locators.get(LocatorName)).size() > 0;
    }

    public  boolean isElementNotPresent(String LocatorName){
        return driver.findElements(Locators.get(LocatorName)).size() == 0;
    }

    public  boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }


    public String getTextOfElement(String LocatorName){
        return driver.findElement(Locators.get(LocatorName)).getText();
    }

    public void dropDownSelect(By field, By options, boolean isDefaultElement, int DefaultElementNumber){
        driver.findElement(field).click();
        List<WebElement> myElements = driver.findElements(options);
        int rnd = DataGenerator.getRandomNumberInRange(0,myElements.size()-1);
        String SelectedElement = myElements.get(rnd).getText();
        if(isDefaultElement && rnd == DefaultElementNumber) {
            driver.findElement(field).click();
        }
            else{
            new Actions(driver).moveToElement(myElements.get(rnd)).perform();
            myElements.get(rnd).click();
        }
        waitForElementLoad(Locators.get("DashboardOnboardingPage.SelectedRegionSelect", SelectedElement));
    }

    public void selectCheckbox(By checkboxes, boolean DefaultElement){
        List<WebElement> myElements = driver.findElements(checkboxes);
        int rnd = DataGenerator.getRandomNumberInRange(0,myElements.size()-1);
        if(DefaultElement & rnd == 0) {

        } else {
            new Actions(driver).moveToElement(myElements.get(rnd)).perform();
            myElements.get(rnd).click();
        }
    }

    public void sendAndCheck(WebElement element, String text){
        Boolean textIsEqual;
        int count = 0;
        do {
            element.clear();
            element.sendKeys(text);
            textIsEqual = element.getAttribute("value").equals(text);
            count++;
        } while (!textIsEqual && count < 5);
    }

    public void clickOnElement(By by){
        WebElement element = driver.findElement(by);
        waitForClickAbleElement(by);
        element.click();
    }

}
