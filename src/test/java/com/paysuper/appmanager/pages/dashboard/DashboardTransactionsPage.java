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
        List<WebElement> svgObject = driver.findElements(Locators.get("DashboardTransactionsPage.LastTransactionSvg"));
        List<WebElement> lastOrder = driver.findElements(Locators.get("DashboardTransactionsPage.LastTransactionUrl"));
        String lastOrderUrl = lastOrder.get(3).getAttribute("pathname");
        Actions builder = new Actions(driver);
        builder.moveToElement(svgObject.get(3)).build().perform();
//        builder.click(svgObject.get(0)).build().perform();
        svgObject.get(3).click();
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
        waitForElementLoad(Locators.get("DashboardTransactionsPage.FirstOrderInTable"));
        driver.findElement(Locators.get("DashboardTransactionsPage.FirstOrderInTable")).click();
        return new OrderPage(driver);
    }


}