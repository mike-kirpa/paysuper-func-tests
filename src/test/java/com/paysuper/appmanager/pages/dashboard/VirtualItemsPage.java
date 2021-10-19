package com.paysuper.appmanager.pages.dashboard;

import com.paysuper.appmanager.helpers.Locators;
import com.paysuper.appmanager.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.File;

public class VirtualItemsPage extends AbstractPage {
    private final String FileName = "File.JPG";

    public VirtualItemsPage(WebDriver driver) {
        super(driver);
        waitForElementLoad("DashboardVirtualItemPage.Headertext");
    }

    public void sendFilePath(){
        File f = new File("src/test/resources/UploadFiles/" + FileName);
        String path = f.getAbsolutePath();
        waitForElementLoad("DashboardVirtualItemPage.ImageInput");
        driver.findElement(Locators.get("DashboardVirtualItemPage.ImageInput")).sendKeys(path);
    }

    public void enterItemName(String ItemName){
        WebElement element = driver.findElement(Locators.get("DashboardVirtualItemPage.ItemNameField"));
        moveToElement(element);
        waitForClickAbleElement(element);
        element.sendKeys(ItemName);
    }

    public void enterShortDesc(String ShortDesc){
        WebElement element = driver.findElement(Locators.get("DashboardVirtualItemPage.ShortDescField"));
        moveToElement(element);
        waitForClickAbleElement(element);
        element.sendKeys(ShortDesc);
    }

    public void enterFullDesc(String FullDesc){
        WebElement element = driver.findElement(Locators.get("DashboardVirtualItemPage.FullDescField"));
        moveToElement(element);
        waitForClickAbleElement(element);
        element.sendKeys(FullDesc);
    }

    public void enterSKU(String SKU){
        WebElement element = driver.findElement(Locators.get("DashboardVirtualItemPage.SKUField"));
        moveToElement(element);
        waitForClickAbleElement(element);
        element.sendKeys(SKU);
    }

    public void enterPrice(String Price){
        WebElement element = driver.findElement(Locators.get("DashboardVirtualItemPage.DefaultCurrencyField"));
        moveToElement(element);
        waitForClickAbleElement(element);
        element.sendKeys(Price);
    }

    public void clickOnAutoPrice(){
        WebElement element = driver.findElement(Locators.get("DashboardVirtualItemPage.AutoCurrencyField"));
        moveToElement(element);
        waitForClickAbleElement(element);
        element.click();
    }

    public void createVI(){
        WebElement element = driver.findElement(Locators.get("DashboardVirtualItemPage.CreateVIButton"));
        moveToElement(element);
        waitForClickAbleElement(element);
        element.click();
    }

    public void createVirtualItem(String ItemName, String ShortDesc, String FullDesc, String SKU, String Price){
        sendFilePath();
        enterItemName(ItemName);
        enterShortDesc(ShortDesc);
        enterFullDesc(FullDesc);
        enterSKU(SKU);
        enterPrice(Price);
        clickOnAutoPrice();
        createVI();
    }


}
