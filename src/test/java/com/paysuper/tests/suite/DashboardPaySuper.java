package com.paysuper.tests.suite;

import com.paysuper.appmanager.pages.dashboard.DashboardLoginPage;

import com.paysuper.appmanager.pages.dashboard.DashboardTransactionsPage;
import junit.framework.Assert;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.paysuper.tests.TestBase;

public class DashboardPaySuper extends TestBase {

    @Test(enabled = false, description="Dashboard Login")
    public void LoginSuccess() {
        app.driver.get(app.getProperties.value("DashboardUrl"));
        DashboardLoginPage dashboardLoginPage = new DashboardLoginPage(app.driver);
        dashboardLoginPage.login(app.getProperties.value("ValidEmail"), app.getProperties.value("Password"));
    }

    @Test(enabled = true, description="Dashboard Refund From Order List")
    public void RefundFromOrderList() {
        app.driver.get(app.getProperties.value("DashboardUrl"));
        DashboardLoginPage dashboardLoginPage = new DashboardLoginPage(app.driver);
        dashboardLoginPage.login(app.getProperties.value("ValidEmail"), app.getProperties.value("Password"));
        app.driver.get(app.getProperties.value("DashboardUrl") + "/transactions");
        DashboardTransactionsPage dashboardTransactionsPage = new DashboardTransactionsPage(app.driver);
        dashboardTransactionsPage.clickOnFilterButton();
        dashboardTransactionsPage.clickOnStatusButton();
        dashboardTransactionsPage.clickOnFilterListItem("Processed");
        dashboardTransactionsPage.clickOnFilterButton();
        String lastOrderUrl = dashboardTransactionsPage.clickRefundOnLastTransaction();
        dashboardTransactionsPage.clickOnConfrimRefundButton();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        app.driver.navigate().refresh();
        dashboardLoginPage.waitForElementLoad("DashboardTransactionsPage.HeaderText");
        Assert.assertFalse(dashboardLoginPage.isElementPresent(By.xpath("//a[@href='" + lastOrderUrl + "']/*/div[contains(@class, 'refund')]")));
    }
}
