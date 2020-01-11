package com.paysuper.appmanager.pages;

import com.paysuper.appmanager.helpers.Locators;
import org.openqa.selenium.WebDriver;

public class DemoSdkPage extends AbstractPage {

    public DemoSdkPage(WebDriver driver) {
        super(driver);
        waitForPageLoad("DemoSdkPage.BuyButton");
    }
    public void clickOnSimpleRadio() {
        driver.findElement(Locators.get("DemoSdkPage.SimpleRadio")).click();
    }

    public void clickOnSimpleProjectField(){
        driver.findElement(Locators.get("DemoSdkPage.SimpleProjectField")).click();
    }

    public void clearSimpleProjectField(){
        driver.findElement(Locators.get("DemoSdkPage.SimpleProjectField")).clear();
    }

    public void enterSimpleProjectField(String projectid){
        driver.findElement(Locators.get("DemoSdkPage.SimpleProjectField")).sendKeys(projectid);
    }

    public void clickOnSimpleAmountField(){
        driver.findElement(Locators.get("DemoSdkPage.SimpleAmountField")).click();
    }

    public void clearSimpleAmountField(){
        driver.findElement(Locators.get("DemoSdkPage.SimpleAmountField")).clear();
    }

    public void enterSimpleAmountField(String amount){
        driver.findElement(Locators.get("DemoSdkPage.SimpleAmountField")).sendKeys(amount);
    }

    public void clickOnSimpleCurrencyField(){
        driver.findElement(Locators.get("DemoSdkPage.SimpleCurrencyField")).click();
    }

    public void clearSimpleCurrencyField(){
        driver.findElement(Locators.get("DemoSdkPage.SimpleCurrencyField")).clear();
    }

    public void enterSimpleCurrencyField(String currency){
        driver.findElement(Locators.get("DemoSdkPage.SimpleCurrencyField")).sendKeys(currency);
    }

    public void clickOnBuyButton(){
        driver.findElement(Locators.get("DemoSdkPage.BuyButton")).click();
    }

    public DemoModalForm createSimpleOrder(String projectid, String amount, String currency){
        clickOnSimpleRadio();
        clearSimpleProjectField();
        enterSimpleProjectField(projectid);
        clearSimpleAmountField();
        enterSimpleAmountField(amount);
        clearSimpleCurrencyField();
        enterSimpleCurrencyField(currency);
        clickOnBuyButton();
        return new DemoModalForm(driver);
    }

}
