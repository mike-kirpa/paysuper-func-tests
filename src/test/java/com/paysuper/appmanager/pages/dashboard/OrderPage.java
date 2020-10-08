package com.paysuper.appmanager.pages.dashboard;

import com.paysuper.appmanager.helpers.Locators;
import com.paysuper.appmanager.models.Order;
import com.paysuper.appmanager.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class OrderPage extends AbstractPage {

    public OrderPage(WebDriver driver) {
        super(driver);
        waitForElementLoad("DashboardOrderPage.HeaderText");
    }

    public String getEmail(){
        moToElement(driver.findElement(Locators.get("DashboardOrderPage.EmailField")));
        return driver.findElement(Locators.get("DashboardOrderPage.EmailField")).getText();
    }

    public String getTotalChargeSum(){
        return driver.findElement(Locators.get("DashboardOrderPage.TotalChargeSumField")).getText();
    }

    public String getBillingAddress(){
        if(driver.findElements(Locators.get("DashboardOrderPage.BillingAddressField")).size() != 0){
            return driver.findElement(Locators.get("DashboardOrderPage.BillingAddressField")).getText();
        }
        else
            return driver.findElement(Locators.get("DashboardOrderPage.CountryField")).getText();
    }

    public String getOrderUid(){
        return driver.findElement(Locators.get("DashboardOrderPage.OrderUidField")).getText();
    }

    public void getRefundOrderUid(Order order){
        if(order.getUUID().equals(driver.findElement(Locators.get("DashboardOrderPage.OriginalTransactionField")).getText())){
            order.setUUID(driver.findElement(Locators.get("DashboardOrderPage.OrderUidField")).getText());
        }
    }

    public void moToElement(WebElement element){
        Actions actions = new Actions(driver);
        actions.moveToElement(element);
        actions.perform();
    }

}
