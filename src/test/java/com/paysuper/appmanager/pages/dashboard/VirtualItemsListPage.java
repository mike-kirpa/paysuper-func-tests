package com.paysuper.appmanager.pages.dashboard;

import com.paysuper.appmanager.helpers.Locators;
import com.paysuper.appmanager.pages.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class VirtualItemsListPage extends AbstractPage {

    public VirtualItemsListPage(WebDriver driver) {
        super(driver);
        waitForElementLoad("DashboardVirtualItemListPage.AddItemButton");
    }

    public VirtualItemsPage clickOnAddItem(){
        clickOnElement(Locators.get("DashboardVirtualItemListPage.AddItemButton"));
        return new VirtualItemsPage(driver);
    }
}
