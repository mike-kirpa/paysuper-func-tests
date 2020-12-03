package com.paysuper.appmanager.pages.dashboard;

import com.paysuper.appmanager.helpers.DataGenerator;
import com.paysuper.appmanager.helpers.Locators;
import com.paysuper.appmanager.models.Email;
import com.paysuper.appmanager.models.Order;
import com.paysuper.appmanager.pages.AbstractPage;
import junit.framework.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class DashboardTransactionsPage extends AbstractPage {
    public DashboardTransactionsPage(WebDriver driver) {
        super(driver);
        waitForElementLoad("DashboardTransactionsPage.HeaderText");
    }

    public String clickRefundOnLastTransaction(){
        waitForClickAbleElement(Locators.get("DashboardTransactionsPage.LastTransactionUrl"));
        WebElement svgObject = driver.findElement(Locators.get("DashboardTransactionsPage.LastTransactionSvg"));
        WebElement lastOrder = driver.findElement(Locators.get("DashboardTransactionsPage.LastTransactionUrl"));
        String lastOrderUrl = lastOrder.getAttribute("pathname");
        Actions builder = new Actions(driver);
        builder.moveToElement(svgObject).build().perform();
        builder.click(svgObject).build().perform();
        return lastOrderUrl;
    }
    public void clickOnConfrimRefundButton(){
        waitForClickAbleElement(Locators.get("DashboardTransactionsPage.ConfirmRefundButton"));
        driver.findElement(Locators.get("DashboardTransactionsPage.ConfirmRefundButton")).click();
        waitForElementLoad("DashboardTransactionsPage.ConfirmAlert");
    }

    public void clickOnFilterButton(){
        WebElement svgObject = driver.findElement(Locators.get("DashboardTransactionsPage.FilterButton"));
        Actions builder = new Actions(driver);
        builder.moveToElement(svgObject).build().perform();
        builder.click(svgObject).build().perform();
    }

    public void clickOnStatusButton(){
        driver.findElement(Locators.get("DashboardTransactionsPage.FilterStatusButton")).click();
    }

    public void clickOnFilterListItem(String filter){
        driver.findElement(By.xpath("//div[contains(text(), '" + filter + "')]")).click();
    }

    public OrderPage openOrderPageByLink(String link){
        driver.get(link);
        return new OrderPage(driver);
    }

    public OrderPage clickOnFirstOrderIntable(){
        driver.findElement(Locators.get("DashboardTransactionsPage.FirstOrderInTable")).click();
        return new OrderPage(driver);
    }


}