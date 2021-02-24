package com.paysuper.appmanager.pages.dashboard;

import com.paysuper.appmanager.helpers.DataGenerator;
import com.paysuper.appmanager.helpers.GetProperties;
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
        waitForClickAbleElement(Locators.get("DashboardTransactionsPage.LastTransactionSvg"));
        List<WebElement> svgObject = driver.findElements(Locators.get("DashboardTransactionsPage.LastTransactionSvg"));
        List<WebElement> lastOrder = driver.findElements(Locators.get("DashboardTransactionsPage.LastTransactionUrl"));
        String lastOrderUrl = driver.findElements(Locators.get("DashboardTransactionsPage.LastTransactionUrl")).get(2).getAttribute("pathname");
        Actions builder = new Actions(driver);
        builder.moveToElement(driver.findElements(Locators.get("DashboardTransactionsPage.LastTransactionSvg")).get(2)).build().perform();
//        builder.click(svgObject.get(0)).build().perform();
        driver.findElements(Locators.get("DashboardTransactionsPage.LastTransactionSvg")).get(2).click();
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

    public OrderPage openOrderByUrl(String url){
        driver.get(url);
        return new OrderPage(driver);
    }

    public OrderPage clickOnLastTransaction(){
        By by = Locators.get("DashboardTransactionsPage.FirstOrderInTable");
        WebElement element = driver.findElement(by);
        waitForElementLoad(by);
        System.out.println(element.getAttribute("pathname"));
        driver.get(GetProperties.value("DashboardUrl") + element.getAttribute("pathname"));
        return new OrderPage(driver);
    }
}