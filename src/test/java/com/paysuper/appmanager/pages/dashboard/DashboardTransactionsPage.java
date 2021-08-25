package com.paysuper.appmanager.pages.dashboard;

import com.paysuper.appmanager.helpers.DataGenerator;
import com.paysuper.appmanager.helpers.GetProperties;
import com.paysuper.appmanager.helpers.Locators;
import com.paysuper.appmanager.models.Email;
import com.paysuper.appmanager.models.Order;
import com.paysuper.appmanager.pages.AbstractPage;
import junit.framework.Assert;
import org.graalvm.compiler.core.common.type.ArithmeticOpTable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.Arrays;
import java.util.List;

public class DashboardTransactionsPage extends AbstractPage {
    public DashboardTransactionsPage(WebDriver driver) {
        super(driver);
        waitForElementLoad("DashboardTransactionsPage.HeaderText");
    }

    public String clickRefundOnLastTransaction(Order order){
        waitForClickAbleElement(Locators.get("DashboardTransactionsPage.LastTransactionSvg"));
        List<WebElement> svgObject = driver.findElements(Locators.get("DashboardTransactionsPage.LastTransactionSvg"));
        List<WebElement> lastOrder = driver.findElements(Locators.get("DashboardTransactionsPage.LastTransactionUrl"));
        WebElement webElement = driver.findElement(Locators.get("DashboardTransactionsPage.LastTransactionUrl"));
        String lastOrderUrl = webElement.getAttribute("pathname");
        String[] separated = lastOrderUrl.split("/");
        lastOrderUrl = "/" + separated[1] + "/transactions/" + separated[2];
        String transactionId = driver.findElement(Locators.get("DashboardTransactionsPage.LastTransactionID")).getText();
        order.setTransactionID(transactionId);
        System.out.println("transactionId:" + order.getTransactionID());
        Actions builder = new Actions(driver);
        builder.moveToElement(driver.findElement(Locators.get("DashboardTransactionsPage.LastTransactionSvg"))).build().perform();
//        builder.click(svgObject.get(0)).build().perform();
        driver.findElement(Locators.get("DashboardTransactionsPage.LastTransactionSvg")).click();
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

    public OrderPage clickOnOrderBytransactionId(String transactionid){
        By by = Locators.get("DashboardTransactionsPage.orderByTransactionID", transactionid);
        WebElement webElement = driver.findElement(by);
        waitForClickAbleElement(webElement);

        String lastOrderUrl = webElement.getAttribute("pathname");
        String[] separated = lastOrderUrl.split("/");
        lastOrderUrl = "/" + separated[1] + "/transactions/" + separated[2];
        driver.get(GetProperties.value("DashboardUrl") + lastOrderUrl);
        //webElement.click();
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

    public void openFilteredOrderList(String filter){
        driver.get(GetProperties.value("DashboardUrl") + "/" + GetProperties.value("merchant_id") + GetProperties.value("FilteredOrderLink") + filter);
    }
}